package ru.itsrv23.coursework2.service;

import ru.itsrv23.coursework2.model.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> findAllExam();
    Exam addExam(Exam exam);
}
