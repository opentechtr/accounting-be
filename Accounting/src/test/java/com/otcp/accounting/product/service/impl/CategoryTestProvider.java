package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.product.entity.Category;

public class CategoryTestProvider {

    public static Category getCategory() {
        Category category = new Category();
        category.setName("test category");
        category.setDescription("test description");
        return category;
    }
}
