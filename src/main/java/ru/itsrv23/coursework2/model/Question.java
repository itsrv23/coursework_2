package ru.itsrv23.coursework2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @JsonIgnore //По сути не нужная аннотация, так как есть DTO
    @Column(name = "deleted")
    private boolean deleted = false;


    @ManyToOne
    @JoinColumn(name = "exam_id", updatable = false)
    private Exam exam;


    public Question() {
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return deleted == question1.deleted
                && question.equals(question1.question)
                && answer.equals(question1.answer)
                && exam.equals(question1.exam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer, deleted, exam);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", deleted=" + deleted +
                ", exam=" + exam +
                '}';
    }
}
