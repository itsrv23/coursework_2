package ru.itsrv23.coursework2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import ru.itsrv23.coursework2.controller.constant.QuestionConstantTest;
import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionControllerWithTestRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private QuestionController questionController;

    @Test
    void contextLoads() {
        assertThat(questionController).isNotNull();
    }

    @Test
    @Sql(value = "/sql/create-exam-before.sql")
    void findQuestions() {
        int countQuestion = 2;
        String url =  "http://localhost:" + port + "/question/1/" + countQuestion;

        ResponseEntity<QuestionResponseDTO[]> forEntity = restTemplate.getForEntity(url, QuestionResponseDTO[].class);
        QuestionResponseDTO[] response = forEntity.getBody();

        assertThat(response.length).isEqualTo(countQuestion);
    }

    @Test
    @Sql(value = "/sql/create-exam-before.sql")
    void findQuestionById() {
        QuestionResponseDTO expected = QuestionConstantTest.expectedResponseDTOById1();
        QuestionResponseDTO actual = restTemplate.getForObject("http://localhost:" + port + "/question/1", QuestionResponseDTO.class);
        assertThat(expected).isEqualTo(actual);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(value = "/sql/create-exam-before.sql")
    void addQuestion() {
        QuestionRequestDTO requestDTO = QuestionConstantTest.requestDTO();
        QuestionResponseDTO expected = QuestionConstantTest.expectedResponseDTO();
        QuestionResponseDTO actual = restTemplate.postForObject("http://localhost:" + port + "/question", requestDTO, QuestionResponseDTO.class);
        assertThat(actual).isEqualTo(expected);


    }

    @Test
    @Sql(value = "/sql/create-exam-before.sql")
    void putQuestion() throws Exception {
        // У restTemplate нет возвратного метода put, если нужно проверить, что вернется в ответ нужно использовать
        // exchange + HttpMethod.PUT, questionRequestDTOHttpEntity

        String resourceUrl = "http://localhost:" + port + "/question";

        QuestionRequestDTO requestDTO = QuestionConstantTest.requestDTO();
        QuestionResponseDTO expected = QuestionConstantTest.expectedResponseDTO();

        HttpEntity<QuestionRequestDTO> questionRequestDTOHttpEntity = new HttpEntity<>(requestDTO);

        ResponseEntity<QuestionResponseDTO> exchange = restTemplate
                .exchange(resourceUrl, HttpMethod.PUT, questionRequestDTOHttpEntity, QuestionResponseDTO.class);
        System.out.println("exchange!!! " + exchange.getBody());
        assertThat(exchange.getStatusCodeValue()).isBetween(200,299);
        QuestionResponseDTO actual = exchange.getBody();
        assertEquals(actual, expected);
    }

    @Test
    @Sql(value = "/sql/create-exam-before.sql")
    void removeQuestion() {
        Long id = 1L;
        String resourceUrl = "http://localhost:" + port + "/question/" + id;
        restTemplate.delete(resourceUrl);

        ResponseEntity<QuestionRequestDTO> forEntity = restTemplate.getForEntity(resourceUrl, QuestionRequestDTO.class);
        assertThat(forEntity.getStatusCodeValue()).isEqualTo(404);
        System.out.println(forEntity);

    }
}