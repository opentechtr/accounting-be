package com.otcp.accounting.product.controller;

import com.otcp.accounting.common.response.ApiResponse;
import com.otcp.accounting.product.dto.request.CreateCategoryDTO;
import com.otcp.accounting.product.dto.request.UpdateCategoryDTO;
import com.otcp.accounting.product.dto.response.CategoryResponseDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public ApiResponse<CategoryResponseDTO> createCategory(@Valid @RequestBody CreateCategoryDTO categoryDTO, Locale locale) {
        CategoryResponseDTO categoryResponseDTO = categoryService.saveCategory(categoryDTO);
        return ApiResponse.success(categoryResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getCategoryById(id)));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping
    public ApiResponse<Category> updateCategory(@Valid @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        return ApiResponse.success(categoryService.updateCategory(updateCategoryDTO));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponse.empty();
    }


    @GetMapping("/search")
    public ApiResponse<List<CategoryResponseDTO>> searchCategoriesByName(@RequestParam String categoryName) {
        return ApiResponse.success(categoryService.searchCategoriesByName(categoryName));
    }
}
