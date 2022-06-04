package ru.itsrv23.coursework2.controller;

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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ExamController.class)
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamRepository examRepository;

    @SpyBean
    private ExamServiceImpl examService;

    @InjectMocks
    private ExamController examController;


    @Test
    void findExam() throws Exception {
        Exam exam = new Exam();
        Long examId = 1L;
        String examName = "exam";

        exam.setId(examId);
        exam.setName(examName);

        when(examRepository.findAll()).thenReturn(List.of(exam));

        mockMvc.perform(get("/exam/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(examId))
                .andExpect(jsonPath("$[0].name").value(examName));
    }

    @Test
    void addExam() {
    }
}