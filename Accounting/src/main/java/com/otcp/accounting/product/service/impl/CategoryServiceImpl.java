package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.DtoConverter;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.request.CreateCategoryDTO;
import com.otcp.accounting.product.dto.response.CategoryResponseDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.repository.CategoryRepository;
import com.otcp.accounting.product.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public CategoryResponseDTO getCategoryById(Long id) {
        return DtoConverter.convert(categoryRepository.getById(id), CategoryResponseDTO.class);
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
