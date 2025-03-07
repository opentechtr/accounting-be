package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.DtoConverter;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.common.response.ApiResponse;
import com.otcp.accounting.common.response.ApiResponseStatus;
import com.otcp.accounting.product.dto.CreateCategoryDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.repository.CategoryRepository;
import com.otcp.accounting.product.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public Category saveCategory(CreateCategoryDTO categoryDTO) {
        Category category = DtoConverter.convert(categoryDTO, Category.class);
        category.setEntityStatus(EntityStatus.ACTIVE);
        return categoryRepository.save(category);
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
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        category.delete();
        categoryRepository.save(category);
    }


    @Override
    public List<Category> searchCategoriesByName(String name) {
        return null;
    }
}
