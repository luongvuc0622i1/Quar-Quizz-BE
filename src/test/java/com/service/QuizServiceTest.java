package com.service;

import com.model.Category;
import com.model.Level;
import com.model.Quiz;
import com.model.TypeQuiz;
import com.repository.ILevelRepository;
import com.repository.category.ICategoryRepository;
import com.repository.quiz.IQuizRepository;
import com.repository.quiz.ITypeRepository;
import com.service.category.CategoryService;
import com.service.category.ICategoryService;
import com.service.quiz.IQuizService;
import com.service.quiz.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.constraints.NotNull;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class QuizServiceTest {
    private IQuizRepository quizRepository = Mockito.mock(IQuizRepository.class);
    private ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
    private ILevelRepository levelRepository = Mockito.mock(ILevelRepository.class);
    private ITypeRepository typeRepository = Mockito.mock(ITypeRepository.class);
    private IQuizService quizService = new QuizService(quizRepository);

    @BeforeEach
    void init() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Java");
        doReturn(Optional.of(category)).when(categoryRepository).findById(1L);
        Set<Category> categories = new HashSet<>();
        doReturn(categories).when(categoryRepository).findAll();

        Level level = new Level();
        level.setId(1L);
        level.setName("Hard");
        levelRepository.save(level);

        TypeQuiz typeQuiz = new TypeQuiz();
        typeQuiz.setId(1L);
        typeQuiz.setName("multipleChoice");
        typeRepository.save(typeQuiz);

        Quiz quiz = new Quiz();
        quiz.setId(1L);
        quiz.setAnswer("A;B;C;D");
        quiz.setCorrect_answer("1;3");
        quiz.setTypeQuizzes(typeQuiz);
        quiz.setCategories(categories);
        quiz.setName("abcd");
        quiz.setLevel(level);
        doReturn(Optional.of(quiz)).when(quizRepository).findById(1L);
        List<Quiz> quizList = Arrays.asList(quiz);
        doReturn(quizList).when(quizRepository).findAll();
    }

    @Test
    @DisplayName("findAll can return list is not null")
    public void whenFindAllNotNull() {
        assertThat(quizService.findAll()).isNotNull();
    }

    @Test
    @DisplayName("findAll can return a list has 1 element")
    public void whenfindAll_thenReturnListHasOneElement() {
        Iterable<Quiz> quizzes = quizService.findAll();
        assertThat(quizzes).hasSize(1);
    }

    @Test
    @DisplayName("findbyID return name abcd")
    public void whenfindById_thenReturnCategory() {
        String name = "abcd";
        Optional<Quiz> quiz = quizService.findById(1L);
        assertThat(quiz.get().getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("findbyID 2L return isPresent")
    public void whenfindById_thenReturnCategoryisPresent() {
        Optional<Quiz> quiz = quizService.findById(2L);
        assertThat(quiz.isPresent()).isFalse();
    }

//    @Test
//    @DisplayName("findbyName return category name Lap trinh co ban")
//    public void whenfindByName_thenReturnCategory() {
//        String name = "abc";
//        Optional<Product> product = productService.findByName("abc");
//        assertThat(product.get().getName()).isEqualTo(name);
//    }

//    @Test
//    @DisplayName("findbyID 2L return isPresent")
//    public void whenfindByName_thenReturnCategoryisPresent() {
//        Optional<Product> product = productService.findByName("abc");
//        assertThat(product.isPresent()).isTrue();
//    }

    @Test
    @DisplayName("save Categories use 1 categoryRepository.save")
    void save() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Java");
        doReturn(Optional.of(category)).when(categoryRepository).findById(1L);
        Set<Category> categories = new HashSet<>();
        doReturn(categories).when(categoryRepository).findAll();

        Level level = new Level();
        level.setId(1L);
        level.setName("Hard");
        levelRepository.save(level);

        TypeQuiz typeQuiz = new TypeQuiz();
        typeQuiz.setId(1L);
        typeQuiz.setName("multipleChoice");
        typeRepository.save(typeQuiz);

        Quiz quiz1 = new Quiz();
        quiz1.setId(1L);
        quiz1.setAnswer("A;B;C;D");
        quiz1.setCorrect_answer("1;3");
        quiz1.setTypeQuizzes(typeQuiz);
        quiz1.setCategories(categories);
        quiz1.setName("abcd");
        quiz1.setLevel(level);
        quizService.save(quiz1);
        verify(quizRepository, times(1)).save(quiz1);
    }

    @Test
    @DisplayName("delete")
    void remove() {
        quizService.remove(2L);
        verify(quizRepository, times(1)).deleteById(2L);
    }
}
