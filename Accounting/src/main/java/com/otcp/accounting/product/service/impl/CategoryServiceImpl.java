package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.DtoConverter;
import com.otcp.accounting.common.exception.EntityConflictException;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.request.CreateCategoryDTO;
import com.otcp.accounting.product.dto.request.UpdateCategoryDTO;
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
    public CategoryResponseDTO saveCategory(CreateCategoryDTO categoryDTO) {
        Category category = DtoConverter.convert(categoryDTO, Category.class);
        category.setEntityStatus(EntityStatus.ACTIVE);

       if (this.categoryRepository.findByName(category.getName()).isPresent()){
           throw new EntityConflictException();
       }

       this.categoryRepository.save(category);
        return DtoConverter.convert(category,CategoryResponseDTO.class);
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
    public Category updateCategory(UpdateCategoryDTO updateCategoryDTO) {
        Category category = getCategory(updateCategoryDTO.getId());

        Optional.ofNullable(updateCategoryDTO.getName())
                .ifPresent(name -> {
                    if (categoryRepository.existsByName(name)) {
                        throw new EntityConflictException();
                    }
                    category.setName(name);
                });

        category.setDescription(updateCategoryDTO.getDescription());

        return categoryRepository.save(category);
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
    public List<CategoryResponseDTO> searchCategoriesByName(String categoryName) {
        List<Category> categoryList = categoryRepository.findAllByNameContainingIgnoreCase(categoryName);
        if (categoryList.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return DtoConverter.convertList(categoryList, CategoryResponseDTO.class);
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
    }
}
