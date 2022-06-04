package ru.itsrv23.coursework2.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.itsrv23.coursework2.controller.dto.QuestionRequestDTO;
import ru.itsrv23.coursework2.controller.dto.QuestionResponseDTO;
import ru.itsrv23.coursework2.service.QuestionService;

import java.util.Set;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{exam}/{amount}")
    public Set<QuestionResponseDTO> findQuestions(@PathVariable Long exam, @PathVariable Integer amount) {
        return questionService.findQuestions(exam, amount);
    }

    @GetMapping("{id}")
    public QuestionResponseDTO findQuestionById(@PathVariable Long id){
        return questionService.findQuestionById(id);
    }

    @PostMapping
    public QuestionResponseDTO addQuestion(@RequestBody QuestionRequestDTO requestDTO){
        return questionService.addQuestion(requestDTO);
    }

    @PutMapping
    public QuestionResponseDTO putQuestion(@RequestBody QuestionRequestDTO requestDTO){
        return questionService.addQuestion(requestDTO);
    }


    // Дописать через patch

    @DeleteMapping({"{id}"})
    public void removeQuestion(@PathVariable Long id){
        questionService.removeQuestion(id);
    }

}
