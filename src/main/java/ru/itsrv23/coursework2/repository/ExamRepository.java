package ru.itsrv23.coursework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itsrv23.coursework2.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
