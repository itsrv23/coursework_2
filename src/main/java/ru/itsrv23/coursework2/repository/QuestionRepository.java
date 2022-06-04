package ru.itsrv23.coursework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsrv23.coursework2.model.Exam;
import ru.itsrv23.coursework2.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository <Question, Long> {
    List<Question> findByExamId(Long examId);
    Optional<Question> findById(Long id);
    List<Question> findAll();
    List<Question> findAllByDeletedFalse();
    Optional<Question> findFirstByExamAndQuestionAndAnswer(Exam exam, String question, String answer);
}
