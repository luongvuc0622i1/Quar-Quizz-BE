package com.controller;

import com.model.Category;
import com.model.Level;
import com.model.Quiz;
import com.model.TypeQuiz;
import com.service.category.ICategoryService;
import com.service.quiz.IQuizService;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuizControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private IQuizService quizService;

    @Autowired
    private ICategoryService categoryService;

    private MockMvc mvc;

    @BeforeEach
    private void init() {
        TypeQuiz typeQuiz = new TypeQuiz();
        typeQuiz.setId(1L);
        typeQuiz.setName("multipleChoice");
        quizService.saveType(typeQuiz);
        Level level = new Level();
        level.setId(1L);
        level.setName("Hard");
        quizService.saveLevel(level);

        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Java");
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("JavaScript");
        categoryService.save(category1);
        categoryService.save(category2);
        doReturn(Optional.of(category1)).when(categoryService).findById(1L);
        Set<Category> categories = new HashSet<>();
        doReturn(categories).when(categoryService).findAll();
//        Iterable iterable = categoryService.findAll();
//        Set<Category> categories = Sets.newHashSet(iterable);

        Quiz quiz1 = new Quiz();
        quiz1.setId(1L);
        quiz1.setAnswer("A;B;C;D");
        quiz1.setCorrect_answer("1;2");
        quiz1.setTypeQuizzes(typeQuiz);
        quiz1.setCategories(categories);
        quiz1.setName("sDewcf2");
        quiz1.setLevel(level);
        quizService.save(quiz1);

        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

//    @Test
//    @WithMockUser(username = "manager", authorities = "MANAGER")
//    @DisplayName("find all return status 200 with role admin")
//    void findAll_whenGetCategoriesWithRoleAdmin_thenReturnStatus200() throws Exception {
//        mvc.perform(get("/manager/quizzes").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(username = "manager", authorities = "MANAGER")
    @DisplayName("find all return status 200 with role manager")
    void findAll_WhenGetCategoriesWithRoleAdmin_ThenReturnStatus200() throws Exception {
        mvc.perform(get("/manager/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
