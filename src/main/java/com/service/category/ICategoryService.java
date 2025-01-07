package com.service.category;

import com.model.Category;
import com.service.IGeneralService;

import java.util.Optional;

public interface ICategoryService extends IGeneralService<Category> {
    Optional<Category> findByName (String name);
}
