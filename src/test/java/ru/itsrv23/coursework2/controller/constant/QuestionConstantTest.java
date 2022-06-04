package ru.itsrv23.coursework2.controller.constant;

import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;

import java.util.Set;

public final class QuestionConstantTest {


    public static QuestionResponseDTO expectedResponseDTOById1() {

        QuestionResponseDTO expected = new QuestionResponseDTO();
        expected.setId(1L);
        expected.setQuestion("Вопрос 1");
        expected.setAnswer("Ответ");
        expected.setExamId(1L);
        expected.setExamName("Java");

        return expected;
    }

    public static QuestionResponseDTO expectedResponseDTO() {
        QuestionResponseDTO expected = new QuestionResponseDTO();
        expected.setId(1L);
        expected.setQuestion("You is ok?");
        expected.setAnswer("I am is ok");
        expected.setExamId(1L);
        expected.setExamName("Java");

        return expected;
    }

    public static Set<QuestionResponseDTO> expectedSetResponseDTO() {
        QuestionResponseDTO expected1 = new QuestionResponseDTO();
        expected1.setId(1L);
        expected1.setQuestion("You is ok?");
        expected1.setAnswer("I am is ok");
        expected1.setExamId(1L);
        expected1.setExamName("Java");

        QuestionResponseDTO expected2 = new QuestionResponseDTO();
        expected2.setId(2L);
        expected2.setQuestion("Вопрос 2");
        expected2.setAnswer("Ответ");
        expected2.setExamId(1L);
        expected2.setExamName("Java");


        return Set.of(expected1, expected2);
    }


    public static QuestionRequestDTO requestDTO() {
        QuestionRequestDTO requestDTO = new QuestionRequestDTO();
        requestDTO.setId(null);
        requestDTO.setQuestion("You is ok?");
        requestDTO.setAnswer("I am is ok");
        requestDTO.setExamId(1L);
        return requestDTO;
    }
}
