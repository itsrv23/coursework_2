package ru.itsrv23.coursework2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itsrv23.coursework2.model.Question;
import ru.itsrv23.coursework2.repository.QuestionRepository;
import ru.itsrv23.coursework2.service.impl.QuestionServiceMultiExamImpl;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.itsrv23.coursework2.controller.constant.ConstantTest.*;


@WebMvcTest(controllers = QuestionController.class)
class QuestionControllerWithMockMvcTest {

    @Autowired
    private ObjectMapper objectMapper; // для получения json быстрее воспользоваться ObjectMapper objectMapper

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    private QuestionServiceMultiExamImpl service;

    @InjectMocks
    private QuestionController questionController;


    @Test
    void findQuestions() throws Exception {
        int countQuestions = 2;
        List<Question> questions = getListQuestion();

        when(questionRepository.findAllByDeletedFalse()).thenReturn(questions);
        mockMvc.perform(get("/question/1/" + countQuestions))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(countQuestions)));
    }

    @Test
    void findQuestionById() throws Exception {

        when(questionRepository.findById(QUESTION_ID1)).thenReturn(Optional.of(getQuestion_ID1()));

        String json = objectMapper.writeValueAsString(getRequestDTO_ID1());

        mockMvc.perform(get("/question/" + QUESTION_ID1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(json));

    }

    @Test
    void addQuestion() throws Exception {
        // лучше использовать any, что бы не было проблем, с NPE
        when(questionRepository.save(any(Question.class))).thenReturn(getQuestion_ID1());

        when(questionRepository.
                findFirstByExamIdAndQuestionAndAnswerAndDeletedIsFalse(anyLong(),
                        anyString(),
                        anyString()))
                .thenReturn(Optional.empty());

        String json = objectMapper.writeValueAsString(getRequestDTO_ID1());

        mockMvc.perform(post("/question")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(json));

        // тест на дубликаты, должна выкинуться ошибка
        when(questionRepository.
                findFirstByExamIdAndQuestionAndAnswerAndDeletedIsFalse(anyLong(),
                        anyString(),
                        anyString()))
                .thenReturn(Optional.of(getQuestion_ID1()));

        mockMvc.perform(post("/question")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    void removeQuestion() throws Exception {
        Question question_id1 = getQuestion_ID1();
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(getQuestion_ID1()));
        question_id1.setDeleted(true);
        when(questionRepository.save(any(Question.class))).thenReturn(question_id1);

        mockMvc.perform(delete("/question/" + QUESTION_ID1))
                .andExpect(content().string(QUESTION_ID1.toString()));
    }
}