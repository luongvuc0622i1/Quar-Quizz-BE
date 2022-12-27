package com.service.quiz;

import com.model.*;
import com.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface IQuizService extends IGeneralService<Quiz> {
    Page<Category> findAllCategory(Pageable pageable);

    Iterable<TypeQuiz> findAllTypeQuiz();

    TypeQuiz saveType(TypeQuiz typeQuiz);

    Page<Level> findAllLevel(Pageable pageable);

    Level saveLevel(Level level);

//    Page<Test> findAllTest(Pageable pageable);

    Iterable<Quiz> findAllQuizByName(String name);

    Page<Quiz> findQuizPage(Pageable pageable);

    Page<Quiz> findQuizByNameContaining(String name, Pageable pageable);

    Iterable<Quiz> findQuizByCategoryContaining(String category);

    Iterable<Quiz> findQuizByTypeContaining(String type);

    Iterable<Quiz> findQuizByLevelContaining(String level);

}
