package com.service.exam;

import com.model.ExamQuiz;
import com.repository.exam.IExamQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ExamQuizService implements IExamQuizService{

    @Autowired
    private IExamQuizRepository examQuizRepository;

    @Override
    public Iterable<ExamQuiz> findAll() {
        return examQuizRepository.findAll();
    }

    @Override
    public Optional<ExamQuiz> findById(Long id) {
        return examQuizRepository.findById(id);
    }

    @Override
    public ExamQuiz save(ExamQuiz model) {
        return examQuizRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        examQuizRepository.deleteById(id);
    }

    public ExamQuiz saveExamQuiz (ExamQuiz model) {
        if (model.getAnswerUser() == model.getQuiz().getCorrect_answer()) {
            model.setStatus(1);
        } else {
            model.setStatus(0);
        }
        return examQuizRepository.save(model);
    }

}
