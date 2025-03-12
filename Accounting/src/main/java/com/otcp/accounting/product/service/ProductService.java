
package com.otcp.accounting.product.service;

import com.otcp.accounting.product.dto.ProductResponseDTO;
import com.otcp.accounting.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    ProductResponseDTO getProductResponseDtoById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Long categoryId);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> searchProductsByName(String name);
}
