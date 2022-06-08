package ru.itsrv23.coursework2.service;

import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;

import java.util.Set;

public interface QuestionService {

    Set<QuestionResponseDTO> findQuestions(Long exam, int amount);
    QuestionResponseDTO addQuestion(QuestionRequestDTO question);
    QuestionResponseDTO findQuestionById(Long id);
    Long removeQuestion(Long id);

}
