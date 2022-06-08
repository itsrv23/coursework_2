package ru.itsrv23.coursework2.service.impl;

import org.springframework.stereotype.Service;
import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;
import ru.itsrv23.coursework2.exception.NotFoundLimitQuestionsException;
import ru.itsrv23.coursework2.exception.NotFoundQuestionException;
import ru.itsrv23.coursework2.exception.QuestionAddException;
import ru.itsrv23.coursework2.exception.QuestionMustBeUniqException;
import ru.itsrv23.coursework2.model.Exam;
import ru.itsrv23.coursework2.model.Question;
import ru.itsrv23.coursework2.repository.ExamRepository;
import ru.itsrv23.coursework2.repository.QuestionRepository;
import ru.itsrv23.coursework2.service.QuestionService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service()
public class QuestionServiceImpl implements QuestionService {

    protected final QuestionRepository questionRepository;


    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public Set<QuestionResponseDTO> findQuestions(Long examId, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Число вопросов должно от 1 и более");
        }
        List<Question> allByExam = questionRepository.findByExamId(examId);
        if (allByExam.size() < amount) {
            throw new NotFoundLimitQuestionsException("Нет достаточного количества вопросов: "
                    + amount + ", имеется: " + allByExam.size());
        }

        Collections.shuffle(allByExam);

        return allByExam.stream()
                .limit(amount)
                .map(QuestionResponseDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public QuestionResponseDTO addQuestion(QuestionRequestDTO requestDTO) {
        Optional<Question> firstByExamAndQuestionAndAnswerAndDeletedIsFalse = questionRepository.
                findFirstByExamIdAndQuestionAndAnswerAndDeletedIsFalse(
                requestDTO.getExamId(),
                requestDTO.getQuestion(),
                requestDTO.getAnswer()
        );
        if (firstByExamAndQuestionAndAnswerAndDeletedIsFalse.isPresent()) {
            throw new QuestionMustBeUniqException();
        }

        Question save = questionRepository.save(newQuestion(requestDTO));
        return new QuestionResponseDTO(save);
    }


    @Override
    public QuestionResponseDTO findQuestionById(Long id) {

        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundQuestionException
                (String.format("Question with id %d not found", id)));

        if (question.isDeleted()) {
            throw new NotFoundQuestionException
                    (String.format("Question with id %d not found. Is Deleted", id));
        }

        return new QuestionResponseDTO(question);
    }

    @Override
    public Long removeQuestion(Long id) {
         questionRepository.deleteById(id);
        return id;
    }

    protected Question newQuestion(QuestionRequestDTO requestDTO) {
        Question question = new Question();
        question.setId(requestDTO.getId());
        question.setQuestion(requestDTO.getQuestion());
        question.setAnswer(requestDTO.getAnswer());
        question.setExam(new Exam(requestDTO.getExamId()));
        return question;
    }


}
