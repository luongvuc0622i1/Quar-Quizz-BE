package com.service;

import com.model.Category;
import com.repository.category.ICategoryRepository;
import com.service.category.CategoryService;
import com.service.category.ICategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    private ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
    private ICategoryService categoryService = new CategoryService(categoryRepository);

    @BeforeEach
    void init() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Java");
        doReturn(Optional.of(category)).when(categoryRepository).findById(1L);
        doReturn(Optional.of(category)).when(categoryRepository).findByName("Java");
        List<Category> categoryList = Arrays.asList(category);
        doReturn(categoryList).when(categoryRepository).findAll();
    }

    @Test
    @DisplayName("findAll can return list is not null")
    public void whenFindAllNotNull() {
        assertThat(categoryService.findAll()).isNotNull();
    }

    @Test
    @DisplayName("findById return category name Java")
    public void whenFindById_thenReturnCategory () {
        String name = "Java";
        Optional<Category> category = categoryService.findById(1L);
        assertThat(category.get().getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("save Category use 1 categoryRepository.save")
    void save() {
        Category category = new Category();
        category.setId(2L);
        category.setName("Python");
        categoryService.save(category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    @DisplayName("delete")
    void remove() {
        categoryService.remove(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }

}
