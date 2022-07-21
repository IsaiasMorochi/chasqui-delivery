package com.imorochi.chasqui.application.service;

import com.imorochi.chasqui.domain.document.Category;
import com.imorochi.chasqui.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void save(final Category category) {
        categoryRepository.save(category);
    }

    public Category findById(final Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

}
