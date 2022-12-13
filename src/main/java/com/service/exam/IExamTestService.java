package com.service.exam;

import com.model.ExamTest;
import com.service.IGeneralService;

public interface IExamTestService extends IGeneralService<ExamTest> {

    Iterable<ExamTest> findExamTestsByUserId(Long user_id);

}
