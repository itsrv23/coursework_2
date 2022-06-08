package ru.itsrv23.coursework2.controller.constant;

import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;
import ru.itsrv23.coursework2.model.Exam;
import ru.itsrv23.coursework2.model.Question;

import java.util.ArrayList;
import java.util.List;

public final class ConstantTest {

    public static final Long QUESTION_ID1 = 1L;

    public static final String QUESTION_1 = "Вопрос 1";
    public static final String ANSWER_1 = "Ответ";

    public static final Long QUESTION_ID2 = 2L;
    public static final String QUESTION_2 = "Вопрос 2";
    public static final String ANSWER_2 = "Ответ";

    public static final Long EXAM_ID1 = 1L;
    public static final String EXAM_NAME = "Java";


    public static QuestionResponseDTO getResponseDTOQuestion_ID1() {

        QuestionResponseDTO expected = new QuestionResponseDTO();
        expected.setId(QUESTION_ID1);
        expected.setQuestion(QUESTION_1);
        expected.setAnswer(ANSWER_1);
        expected.setExamId(EXAM_ID1);
        expected.setExamName(EXAM_NAME);

        return expected;
    }


    public static List<Question> getListQuestion() {
        List<Question> list = new ArrayList<>();
        Exam exam = new Exam(EXAM_ID1);
        exam.setName(EXAM_NAME);

        Question expected1 = new Question();
        expected1.setId(QUESTION_ID1);
        expected1.setQuestion(QUESTION_1);
        expected1.setAnswer(ANSWER_1);
        expected1.setExam(exam);

        Question expected2 = new Question();
        expected2.setId(QUESTION_ID2);
        expected2.setQuestion(QUESTION_2);
        expected2.setAnswer(ANSWER_2);
        expected2.setExam(exam);
        list.add(expected1);
        list.add(expected2);

        return list;
    }

    public static Question getQuestion_ID1() {
        Exam exam = new Exam(EXAM_ID1);
        exam.setName(EXAM_NAME);

        Question expected1 = new Question();
        expected1.setId(QUESTION_ID1);
        expected1.setQuestion(QUESTION_1);
        expected1.setAnswer(ANSWER_1);
        expected1.setExam(exam);

        return expected1;
    }


    public static QuestionRequestDTO getRequestDTO_ID1() {
        QuestionRequestDTO requestDTO = new QuestionRequestDTO();
        requestDTO.setId(QUESTION_ID1);
        requestDTO.setQuestion(QUESTION_1);
        requestDTO.setAnswer(ANSWER_1);
        requestDTO.setExamId(EXAM_ID1);
        return requestDTO;
    }


    public static Exam getExamID1() {
        Exam exam = new Exam();
        exam.setId(EXAM_ID1);
        exam.setName(EXAM_NAME);
        return exam;
    }
}
