package ru.itsrv23.coursework2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static ru.itsrv23.coursework2.controller.constant.ConstantTest.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionControllerWithTestRestTemplateTest {
    private final String sql = "/sql/create-exam-and-questions-before.sql";

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
    @Sql(sql)
    void findQuestions() {
        int countQuestion = 2;
        String url =  "http://localhost:" + port + "/question/" + EXAM_ID1 + "/" + countQuestion;

        ResponseEntity<QuestionResponseDTO[]> forEntity = restTemplate.getForEntity(url, QuestionResponseDTO[].class);
        QuestionResponseDTO[] response = forEntity.getBody();

        assertThat(response.length).isEqualTo(countQuestion);
    }

    @Test
    @Sql(sql)
    void findQuestionById() {
        QuestionResponseDTO expected = getResponseDTOQuestion_ID1();
        QuestionResponseDTO actual = restTemplate.
                getForObject("http://localhost:" + port + "/question/" + QUESTION_ID1, QuestionResponseDTO.class);
        assertThat(expected).isEqualTo(actual);
        assertEquals(expected, actual);
    }

    @Test
    @Sql("/sql/create-exam-before.sql")
    void addQuestion() {
        QuestionRequestDTO requestDTO = getRequestDTO_ID1();
        QuestionResponseDTO expected = getResponseDTOQuestion_ID1();
        QuestionResponseDTO actual = restTemplate.postForObject("http://localhost:" + port + "/question",
                requestDTO,
                QuestionResponseDTO.class);
        assertThat(actual).isEqualTo(expected);
    }



    @Test
    @Sql(sql)
    void removeQuestion() {
        String resourceUrl = "http://localhost:" + port + "/question/" + QUESTION_ID1;
        restTemplate.delete(resourceUrl);
        ResponseEntity<QuestionRequestDTO> forEntity = restTemplate.getForEntity(resourceUrl, QuestionRequestDTO.class);
        assertThat(forEntity.getStatusCodeValue()).isEqualTo(404);
        System.out.println(forEntity);

    }
}