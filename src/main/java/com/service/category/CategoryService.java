package com.service.category;

import com.model.Category;
import com.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryService implements ICategory{
    @Autowired
    private ICategoryRepository CategoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return CategoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return CategoryRepository.findById(id);
    }

    @Override
    public Category save(Category model) {
        return CategoryRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        CategoryRepository.deleteById(id);
    }
}
