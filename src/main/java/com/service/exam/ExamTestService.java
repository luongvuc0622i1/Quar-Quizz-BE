package com.service.exam;

import com.model.ExamQuiz;
import com.model.ExamTest;
import com.repository.exam.IExamTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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


    private ExamTest examTest;


//    @Override
//    public void changeNumberAnswer(Optional<ExamTest> examTest) {
//
//        Set<ExamQuiz> examQuizzes = examTest.get().getExamQuizzes();
//        examQuizzes.forEach((e) -> {
//            int count = 0;
//            if (e.getStatus() == 1) {
//               count++;
//            }
//            examTest.get().setNumOfTA(count);
//        });
//        examTestRepository.save(examTest.get());
//    }

}
