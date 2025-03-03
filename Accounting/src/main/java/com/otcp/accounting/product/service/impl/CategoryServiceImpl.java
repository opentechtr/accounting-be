package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public Category saveCategory(Category category) {
        return null;
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public List<Category> searchCategoriesByName(String name) {
        return null;
    }
}
