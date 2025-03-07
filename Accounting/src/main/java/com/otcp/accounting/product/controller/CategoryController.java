package com.otcp.accounting.product.controller;

import com.otcp.accounting.common.config.LocalizationConfig;
import com.otcp.accounting.common.response.ApiResponse;
import com.otcp.accounting.product.dto.CreateCategoryDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final MessageSource messageSource;




    @PostMapping
    public ApiResponse<Category> createCategory(@RequestBody CreateCategoryDTO categoryDTO, Locale locale) {
        Category category = categoryService.saveCategory(categoryDTO);
        String successMessage = messageSource.getMessage("CATEGORY_CREATE_SUCCESS_MESSAGE", null, locale);
        return ApiResponse.success(category, successMessage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long id, Locale locale) {
        categoryService.deleteCategory(id);
        String message = messageSource.getMessage("CATEGORY_DELETE_SUCCESS_MESSAGE", null, locale);
        return ApiResponse.success(null, message);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategoriesByName(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.searchCategoriesByName(name));
    }
}
