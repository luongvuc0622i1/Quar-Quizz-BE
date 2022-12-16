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

    @Override
    public void saveExamQuiz (Optional<ExamQuiz> model) {
        String answerUser = model.get().getAnswerUser();
        String correctAnswer = model.get().getQuiz().getCorrect_answer();
        if (answerUser.equals(correctAnswer)) {
            model.get().setStatus(1);
        } else {
            model.get().setStatus(0);
        }
         examQuizRepository.save(model.get());
    }



}
