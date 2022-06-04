package ru.itsrv23.coursework2.service.impl;

import org.springframework.stereotype.Service;
import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;
import ru.itsrv23.coursework2.exception.NotFoundLimitQuestionsException;
import ru.itsrv23.coursework2.exception.NotFoundQuestionException;
import ru.itsrv23.coursework2.exception.QuestionAddException;
import ru.itsrv23.coursework2.model.Exam;
import ru.itsrv23.coursework2.model.Question;
import ru.itsrv23.coursework2.repository.ExamRepository;
import ru.itsrv23.coursework2.repository.QuestionRepository;
import ru.itsrv23.coursework2.service.QuestionService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service()
public class QuestionServiceImpl implements QuestionService {

    protected final QuestionRepository questionRepository;
    protected final ExamRepository examRepository;


    public QuestionServiceImpl(QuestionRepository questionRepository, ExamRepository examRepository) {
        this.questionRepository = questionRepository;
        this.examRepository = examRepository;
    }



    @Override
    public Set<QuestionResponseDTO> findQuestions(Long examId, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Число вопросов должно от 1 и более");
        }
        List<Question> allByExam = questionRepository.findByExamId(examId);
        if (allByExam.size() < amount){
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
        try {
            Question save = questionRepository
                    .save(newQuestion(requestDTO)
                    );
            return new QuestionResponseDTO(save);
        } catch (RuntimeException runtimeException){
            // может упасть потому что не соблюдается уникальность на уровне базы
            // хорошо или плохо выносить логику на уровень базы под вопросом
            // если эта таблица очень большая, каждый раз проверять если ли дубликат перед добавлением - больно
            // o.h.engine.jdbc.spi.SqlExceptionHelper   : ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности "question_uniq_examid_question_answer"

            // Так я могу пробросить 400, а не 500 ошибку
            throw  new QuestionAddException();
        }
    }

    @Override
    public QuestionResponseDTO patchQuestion(QuestionRequestDTO question) {
        // TODO:
        //  Исправить на Patch, с частичным обновлением полей
        Question save = questionRepository.save(newQuestion(question));
        return new QuestionResponseDTO(save);
    }

    @Override
    public QuestionResponseDTO findQuestionById(Long id) {

        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundQuestionException
                (String.format("Question with id %d not found", id)));

        if (question.isDeleted()){
            throw new NotFoundQuestionException
                    (String.format("Question with id %d not found. Is Deleted", id));
        }

        return new QuestionResponseDTO(question);
    }

    @Override
    public void removeQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    protected Question newQuestion(QuestionRequestDTO requestDTO){
        Question question = new Question();
        question.setId(requestDTO.getId());
        question.setQuestion(requestDTO.getQuestion());
        question.setAnswer(requestDTO.getAnswer());
        question.setExam(new Exam(requestDTO.getExamId()));
        return question;
    }


}
