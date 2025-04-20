package com.otcp.accounting.product.service;

import com.otcp.accounting.product.dto.request.CreateCategoryDTO;
import com.otcp.accounting.product.dto.response.CategoryResponseDTO;
import com.otcp.accounting.product.entity.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(CreateCategoryDTO categoryDTO);
    CategoryResponseDTO getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    List<Category> searchCategoriesByName(String name);
    Category getCategory(Long categoryId);
}
