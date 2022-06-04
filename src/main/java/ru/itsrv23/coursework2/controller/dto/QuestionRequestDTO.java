package ru.itsrv23.coursework2.controller.dto;

import java.util.Objects;

public class QuestionRequestDTO {
    private Long id;
    private String question;
    private String answer;
    private Long examId;

    public QuestionRequestDTO() {
    }

    public QuestionRequestDTO(Long id, String question, String answer, Long examId) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.examId = examId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionRequestDTO that = (QuestionRequestDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(question, that.question) && Objects.equals(answer, that.answer) && Objects.equals(examId, that.examId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, examId);
    }

    @Override
    public String toString() {
        return "QuestionRequestDTO{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", examId=" + examId +
                '}';
    }
}
