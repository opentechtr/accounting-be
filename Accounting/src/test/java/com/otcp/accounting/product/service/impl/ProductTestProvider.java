package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.product.dto.ProductRequestDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.entity.Product;

import java.math.BigDecimal;

public class ProductTestProvider {
    public static ProductRequestDTO createProductRequestDTO() {
        ProductRequestDTO productDTO = new ProductRequestDTO();
        productDTO.setName("Test Product");
        productDTO.setCode("P123");
        productDTO.setPrice(BigDecimal.valueOf(100.0));
        productDTO.setCategoryId(1L);
        return productDTO;
    }
    public static Product getProduct(ProductRequestDTO productDTO, Category category) {


        Product product = new Product();
        product.setId(1L);
        product.setName(productDTO.getName());
        product.setCode(productDTO.getCode());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return product;
    }

    public static Category getCategory() {
        Category category = new Category();
        category.setId(1L);
        return category;
    }
}
