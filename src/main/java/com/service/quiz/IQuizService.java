package com.service.quiz;

import com.model.*;
import com.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface IQuizService extends IGeneralService<Quiz> {
    Page<Category> findAllCategory(Pageable pageable);

    Page<TypeQuiz> findAllTypeQuiz(Pageable pageable);

    Page<Level> findAllLevel(Pageable pageable);

//    Page<Test> findAllTest(Pageable pageable);

    Iterable<Quiz> findAllQuizByName(String name);

    Page<Quiz> findQuizPage(Pageable pageable);

    Page<Quiz> findQuizByNameContaining(String name, Pageable pageable);

    Page<Quiz> findQuizByCategoryContaining(String category, Pageable pageable);

    Page<Quiz> findQuizByTypeContaining(String type, Pageable pageable);

    Page<Quiz> findQuizByLevelContaining(String level, Pageable pageable);

}
