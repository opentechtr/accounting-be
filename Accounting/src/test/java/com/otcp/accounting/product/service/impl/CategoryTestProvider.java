package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.product.dto.request.CreateCategoryDTO;
import com.otcp.accounting.product.entity.Category;

public class CategoryTestProvider {

    public static Category getCategory() {
        Category category = new Category();
        category.setName("test category");
        category.setDescription("test description");
        return category;
    }

    public static Category getCategory(CreateCategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    public static CreateCategoryDTO createCategoryRequestDTO() {
        CreateCategoryDTO categoryDTO = new CreateCategoryDTO();
        categoryDTO.setName("test category");
        categoryDTO.setDescription("test description");
        return categoryDTO;
    }
}
