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
import ru.itsrv23.coursework2.model.Exam;
import ru.itsrv23.coursework2.repository.ExamRepository;
import ru.itsrv23.coursework2.service.impl.ExamServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.itsrv23.coursework2.controller.constant.ConstantTest.*;


@WebMvcTest(controllers = ExamController.class)
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamRepository examRepository;

    @SpyBean
    private ExamServiceImpl examService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ExamController examController;


    @Test
    void findExam() throws Exception {

        when(examRepository.findAll()).thenReturn(List.of(getExamID1()));

        mockMvc.perform(get("/exam/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(EXAM_ID1))
                .andExpect(jsonPath("$[0].name").value(EXAM_NAME));
    }

    @Test
    void addExam() throws Exception {
        when(examRepository.save(any(Exam.class))).thenReturn(getExamID1());
        String json = objectMapper.writeValueAsString(getExamID1());

        mockMvc.perform(post("/exam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(print())
                .andExpect(content().json(json));


    }
}