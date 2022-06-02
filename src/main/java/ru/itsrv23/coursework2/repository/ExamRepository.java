package ru.itsrv23.coursework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsrv23.coursework2.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
