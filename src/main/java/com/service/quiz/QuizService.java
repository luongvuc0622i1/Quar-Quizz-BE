package com.service.quiz;

import com.model.*;
import com.repository.ILevelRepository;
import com.repository.category.ICategoryRepository;
import com.repository.quiz.IQuizRepository;
import com.repository.quiz.ITypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements IQuizService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IQuizRepository quizRepository;

    @Autowired
    private ITypeRepository typeRepository;

    @Autowired
    private ILevelRepository levelRepository;
    @Override
    public Iterable<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Quiz save(Quiz model) {
        return quizRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public Page<Category> findAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<TypeQuiz> findAllTypeQuiz(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Page<Level> findAllLevel(Pageable pageable) {
        return levelRepository.findAll(pageable);
    }

//    @Override
//    public Page<Test> findAllTest(Pageable pageable) {
//        return ;
//    }

    @Override
    public Iterable<Quiz> findAllQuizByName(String name) {
        return quizRepository.findAll();
    }

    @Override
    public Page<Quiz> findQuizPage(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    @Override
    public Page<Quiz> findQuizByNameContaining(String name, Pageable pageable) {
        return quizRepository.findQuizzesByNameContaining(name, pageable);
    }

    @Override
    public Iterable<Quiz> findQuizByCategoryContaining(String category) {
        return quizRepository.findQuizzesByCategoriesContaining(category);
    }

    @Override
    public Iterable<Quiz> findQuizByTypeContaining(String type) {

        return quizRepository.findQuizzesByTypesContaining(type);
    }

    @Override
    public Iterable<Quiz> findQuizByLevelContaining(String level) {

        return quizRepository.findQuizzesByLevelContaining(level);
    }
}
