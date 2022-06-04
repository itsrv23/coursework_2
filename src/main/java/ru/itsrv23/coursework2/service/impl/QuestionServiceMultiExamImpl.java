package ru.itsrv23.coursework2.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;
import ru.itsrv23.coursework2.exception.NotFoundQuestionException;
import ru.itsrv23.coursework2.model.Question;
import ru.itsrv23.coursework2.repository.ExamRepository;
import ru.itsrv23.coursework2.repository.QuestionRepository;
import ru.itsrv23.coursework2.service.QuestionService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/*
* Данная имплементация ищет вопросы по всем экзаменам
* Метод удаления не удаляет данные из базы, а проставляет метку deleted=t
* */
@Service("questionServiceVer2")
@Primary
public class QuestionServiceMultiExamImpl extends QuestionServiceImpl implements QuestionService {

    public QuestionServiceMultiExamImpl(QuestionRepository questionRepository, ExamRepository examRepository) {
        super(questionRepository, examRepository);
    }

    @Override
    public Set<QuestionResponseDTO> findQuestions(Long examId, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Число вопросов должно от 1 и более");
        }
        // Данная имплементация ищет вопросы по всем экзаменам
        List<Question> allByExam = questionRepository.findAllByDeletedFalse();
        Collections.shuffle(allByExam);

        return allByExam.stream()
                .limit(amount)
                .map(QuestionResponseDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public void removeQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(NotFoundQuestionException::new);
        question.setDeleted(true);
        questionRepository.save(question);
    }
}
