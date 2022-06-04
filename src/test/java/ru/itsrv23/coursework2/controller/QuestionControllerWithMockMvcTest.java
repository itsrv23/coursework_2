package ru.itsrv23.coursework2.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.itsrv23.coursework2.service.QuestionService;

import static org.assertj.core.api.Assertions.assertThat;


@WebMvcTest(controllers = QuestionController.class)
class QuestionControllerWithMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService service;

    @InjectMocks
    QuestionController questionController;


    @Test
    void findQuestions() {
    }

    @Test
    void findQuestionById() {

    }

    @Test
    void addQuestion() {
    }

    @Test
    void putQuestion() {
    }

    @Test
    void removeQuestion() {
    }
}