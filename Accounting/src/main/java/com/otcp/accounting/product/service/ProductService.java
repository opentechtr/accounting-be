
package com.otcp.accounting.product.service;

import com.otcp.accounting.product.dto.ProductResponseDTO;
import com.otcp.accounting.product.entity.Product;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ProductService {
    ProductResponseDTO saveProduct(ProductResponseDTO productResponseDTO);
    ProductResponseDTO getProductResponseDtoById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Long categoryId);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> searchProductsByName(String name);
}
