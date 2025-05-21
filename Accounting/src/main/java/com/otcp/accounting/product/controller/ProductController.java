
package com.otcp.accounting.product.controller;

import com.otcp.accounting.common.response.ApiResponse;
import com.otcp.accounting.product.dto.request.ProductRequestDTO;
import com.otcp.accounting.product.dto.request.ProductUpdateDTO;
import com.otcp.accounting.product.dto.response.ProductResponseDTO;
import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping
    public ApiResponse<ProductResponseDTO> saveProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO, Locale locale) {
        ProductResponseDTO productResponseDTO=productService.saveProduct(productRequestDTO);
        return ApiResponse.success(productResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductResponseDtoById(id));

    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<List<ProductResponseDTO>> getProductsByCategory(@PathVariable Long categoryId) {
        return ApiResponse.success(productService.getProductsByCategory(categoryId));
    }

    @PutMapping
    public ApiResponse<ProductResponseDTO> updateProduct(@Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        return ApiResponse.success(productService.updateProduct(productUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ApiResponse.noContent();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchProductsByName(name));
    }
}
