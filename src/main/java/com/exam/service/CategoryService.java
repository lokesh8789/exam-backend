package com.exam.service;

import com.exam.entity.Category;

import java.util.Set;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Category category,Long id);
    Set<Category> getCategories();
    Category getCategory(Long id);
    void deleteCategory(Long id);
}
