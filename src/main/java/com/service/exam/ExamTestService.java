package com.service.exam;

import com.model.ExamTest;
import com.repository.exam.IExamTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamTestService implements IExamTestService{

    @Autowired
    private IExamTestRepository examTestRepository;

    @Override
    public Iterable<ExamTest> findAll() {
        return examTestRepository.findAll();
    }

    @Override
    public Optional<ExamTest> findById(Long id) {
        return examTestRepository.findById(id);
    }

    @Override
    public ExamTest save(ExamTest model) {
        return examTestRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        examTestRepository.deleteById(id);
    }

    @Override
    public Iterable<ExamTest> findExamTestsByUserId(Long user_id) {
        return examTestRepository.findExamTestsByAppUser(user_id);
    }
}
