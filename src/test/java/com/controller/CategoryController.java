package com.controller;

import com.model.Category;
import com.service.category.ICategoryService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryController {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ICategoryService categoryService;

    private MockMvc mvc;

    @BeforeEach
    private void init() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Java");
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Java");
        categoryService.save(category1);
        categoryService.save(category2);

        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "manager", authorities = "MANAGER")
    @DisplayName("find all return status 200 with role manager")
    void findAll_WhenGetCategoriesWithRoleAdmin_ThenReturnStatus200() throws Exception {
        mvc.perform(get("/manager/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
