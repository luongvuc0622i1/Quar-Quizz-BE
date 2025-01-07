package com.service.category;

import com.model.Category;
import com.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Long> findQuizIdByCategoryId(Long categoryId){
        return categoryRepository.findQuizsIdByCategoryId(categoryId);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category finAll(){
        return (Category) categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
    public Category byId(Long id){
        return categoryRepository.findById(id).get();
    }
    @Override
    public Category save(Category model) {
        return categoryRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
