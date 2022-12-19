package com.service.exam;

import com.model.ExamTest;
import com.service.IGeneralService;

import java.util.Optional;

public interface IExamTestService extends IGeneralService<ExamTest> {

    Iterable<ExamTest> findExamTestsByUserId(Long user_id);

//    void changeNumberAnswer (Optional<ExamTest> examTest);

}
