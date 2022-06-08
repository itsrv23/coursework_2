package ru.itsrv23.coursework2.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.itsrv23.coursework2.model.Exam;
import ru.itsrv23.coursework2.repository.ExamRepository;
import ru.itsrv23.coursework2.service.ExamService;

import java.util.List;

@Service
@Primary
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public List<Exam> findAllExam() {
        return examRepository.findAll();
    }

    @Override
    public Exam addExam(Exam exam) {
        return examRepository.save(exam);
    }
}
