package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.exception.DuplicateException;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.ProductResponseDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.repository.CategoryRepository;
import com.otcp.accounting.product.repository.ProductRepository;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void saveProduct_ShouldSaveSuccessfully()  {
        // Arrange
        ProductResponseDTO productDTO = new ProductResponseDTO();
        productDTO.setName("Test Product");
        productDTO.setCode("P123");
        productDTO.setPrice(BigDecimal.valueOf(100.0));
        productDTO.setCategoryId(1L);

        Category category = new Category();
        category.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setName(productDTO.getName());
        product.setCode(productDTO.getCode());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        Mockito.when(productRepository.findByCode("P123")).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        // Act
        ProductResponseDTO result = productService.saveProduct(productDTO);

        // Assert
        assertNotNull(result);
        assertEquals("P123", result.getCode());
    }

    @Test
    void saveProduct_ShouldThrowDuplicateException_WhenProductCodeExists() {
        // Arrange
        ProductResponseDTO productDTO = new ProductResponseDTO();
        productDTO.setCode("P123");
        productDTO.setCategoryId(1L); // DİKKAT: 1L

        Mockito.when(productRepository.findByCode("P123")).thenReturn(Optional.of(new Product()));
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(new Category())); // DİKKAT: aynı ID

        // Act & Assert
        assertThrows(DuplicateException.class, () -> productService.saveProduct(productDTO));
    }

    @Test
    void saveProduct_ShouldThrowEntityNotFoundException_WhenCategoryNotFound() {
        // Arrange
        ProductResponseDTO productDTO = new ProductResponseDTO();
        productDTO.setCode("P999");
        productDTO.setCategoryId(999L); // olmayan kategori

        Mockito.when(productRepository.findByCode("P999")).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> productService.saveProduct(productDTO));
    }
}
