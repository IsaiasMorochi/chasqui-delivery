package com.imorochi.chasqui.infrastructure.controller;

import com.imorochi.chasqui.application.service.CategoryService;
import com.imorochi.chasqui.domain.document.Category;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public void save(@RequestBody final Category category) {
        categoryService.save(category);
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable final Integer id) {
        return categoryService.findById(id);
    }

}
