package ru.itsrv23.coursework2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itsrv23.coursework2.controller.constant.QuestionConstantTest;
import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.model.Question;
import ru.itsrv23.coursework2.repository.QuestionRepository;
import ru.itsrv23.coursework2.service.impl.QuestionServiceMultiExamImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = QuestionController.class)
class QuestionControllerWithMockMvcTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionRepository questionRepository;

    @SpyBean
    QuestionServiceMultiExamImpl service;

    @InjectMocks
    QuestionController questionController;


    @Test
    void findQuestions() throws Exception {
        List<Question> questions = QuestionConstantTest.expectedListQuestion();

        when(questionRepository.findAllByDeletedFalse()).thenReturn(questions);
        mockMvc.perform(get("/question/1/2"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void findQuestionById() throws Exception {
        when(questionRepository.findById(1L)).thenReturn(QuestionConstantTest.expectedQuestion());
        mockMvc.perform(get("/question/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.question").value("You is ok?"));

    }

    @Test
    void addQuestion() throws Exception {
        Question question = QuestionConstantTest.expectedQuestion().get();
        // лучше использовать any, что бы не было проблем, с NPE
        when(questionRepository.save(ArgumentMatchers.any(Question.class)))
                .thenReturn(question);

        when(questionRepository.
                findFirstByExamIdAndQuestionAndAnswerAndDeletedIsFalse(anyLong(),
                        anyString(),
                        anyString()))
                .thenReturn(Optional.empty());

        // для получения json быстрее воспользоваться ObjectMapper objectMapper
        String json = objectMapper.writeValueAsString(QuestionConstantTest.requestDTO());

        mockMvc.perform(post("/question")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(json));
                // что бы не проверять по отдельным полям, можно проверить json == json
                //.andExpect(jsonPath("$.question").value("You is ok?"));

        // тест на дубликаты, должна выкинуться ошибка
        when(questionRepository.
                findFirstByExamIdAndQuestionAndAnswerAndDeletedIsFalse(anyLong(),
                        anyString(),
                        anyString()))
                .thenReturn(Optional.of(question));

        mockMvc.perform(post("/question")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Ignore
    @Test
    void putQuestion() {
        // пока игнорим, метод дублирует POST
    }

    @Ignore
    @Test
    void removeQuestion() {
        // не придумал как тестировать void методы, без реальной базы
    }
}