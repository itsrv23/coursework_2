package ru.itsrv23.coursework2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;
import ru.itsrv23.coursework2.service.QuestionService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = "/sql/create-exam-before.sql")
class Coursework2ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    QuestionService questionService;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        QuestionResponseDTO questionById = questionService.findQuestionById(1L);
        System.out.println(questionById);

    }

}
