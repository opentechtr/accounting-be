package com.otcp.accounting.product.service;

import com.otcp.accounting.product.dto.request.CreateCategoryDTO;
import com.otcp.accounting.product.dto.request.UpdateCategoryDTO;
import com.otcp.accounting.product.dto.response.CategoryResponseDTO;
import com.otcp.accounting.product.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO saveCategory(CreateCategoryDTO categoryDTO);
    CategoryResponseDTO getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(UpdateCategoryDTO updateCategoryDTO);
    void deleteCategory(Long id);
    List<CategoryResponseDTO> searchCategoriesByName(String categoryName);
    Category getCategory(Long categoryId);
}
