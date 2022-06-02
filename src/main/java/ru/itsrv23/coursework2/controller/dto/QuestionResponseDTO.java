package ru.itsrv23.coursework2.controller.dto;

import ru.itsrv23.coursework2.model.Question;

public class QuestionResponseDTO {
    private Long id;
    private String question;
    private String answer;
    private Long examId;
    private String examName;

    public QuestionResponseDTO() {
    }

    public QuestionResponseDTO(Long id, String question, String answer, Long examId, String examName) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.examId = examId;
        this.examName = examName;
    }

    public QuestionResponseDTO(Question question){
        this.id = question.getId();
        this.question = question.getQuestion();
        this.answer = question.getAnswer();
        this.examId = question.getExam().getId();
        this.examName = question.getExam().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    @Override
    public String toString() {
        return "QuestionResponseDTO{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", examId=" + examId +
                '}';
    }
}
