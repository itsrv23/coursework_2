package ru.itsrv23.coursework2.controller;


import org.springframework.web.bind.annotation.*;
import ru.itsrv23.coursework2.model.Exam;
import ru.itsrv23.coursework2.service.ExamService;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {


    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/get")
    public List<Exam> findExam() {
        return examService.findAllExam();
    }

    @PostMapping
    public Exam addExam(@RequestBody Exam exam){
        return examService.addExam(exam);
    }


    @GetMapping
    public String printGritting() {
        return "GET /exam/get  - все экзамены <br>" +
                "GET /exam/get/{exam}/{amount} <br>";
    }


}
