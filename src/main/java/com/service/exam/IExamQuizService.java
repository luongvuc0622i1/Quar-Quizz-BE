package com.service.exam;

import com.model.ExamQuiz;
import com.service.IGeneralService;

import java.util.Optional;

public interface IExamQuizService extends IGeneralService<ExamQuiz> {
    void saveExamQuiz (Optional<ExamQuiz> model);
}
