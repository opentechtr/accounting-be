
package com.otcp.accounting.product.service;

import com.otcp.accounting.product.dto.request.ProductRequestDTO;
import com.otcp.accounting.product.dto.request.ProductUpdateDTO;
import com.otcp.accounting.product.dto.response.ProductResponseDTO;
import com.otcp.accounting.product.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO getProductResponseDtoById(Long id);
    List<Product> getAllProducts();
    List<ProductResponseDTO> getProductsByCategory(Long categoryId);
    ProductResponseDTO updateProduct(ProductUpdateDTO productUpdateDTO);
    void deleteProduct(Long id);
    List<Product> searchProductsByName(String name);
}
