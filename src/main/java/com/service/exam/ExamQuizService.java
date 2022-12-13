package com.service.exam;

import com.model.ExamQuiz;
import com.repository.exam.ExamQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ExamQuizService implements IExamQuizService{

    @Autowired
    private ExamQuizRepository examQuizRepository;

    @Override
    public Iterable<ExamQuiz> findAll() {
        return examQuizRepository.findAll();
    }

    @Override
    public Optional<ExamQuiz> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ExamQuiz save(ExamQuiz model) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
