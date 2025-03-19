package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.exception.EntityConflictEexception;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.request.ProductRequestDTO;
import com.otcp.accounting.product.dto.response.ProductResponseDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.repository.CategoryRepository;
import com.otcp.accounting.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static com.otcp.accounting.product.service.impl.ProductTestProvider.*;
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

        ProductRequestDTO productDTO = createProductRequestDTO();
        Category category = getCategory();
        Product product = getProduct(productDTO,category);


        Mockito.when(productRepository.findByCode("P123")).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);


        ProductResponseDTO result = productService.saveProduct(productDTO);
        assertNotNull(result);
        assertEquals("P123", result.getCode());
    }

    @Test
    void saveProduct_ShouldThrowDuplicateException_WhenProductCodeExists() {

        ProductRequestDTO productDTO = new ProductRequestDTO();
        productDTO.setCode("P123");
        productDTO.setCategoryId(1L);

        Mockito.when(productRepository.findByCode("P123")).thenReturn(Optional.of(new Product()));
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(new Category())); // DİKKAT: aynı ID


        assertThrows(EntityConflictEexception.class, () -> productService.saveProduct(productDTO));
    }

    @Test
    void saveProduct_ShouldThrowEntityNotFoundException_WhenCategoryNotFound() {

        ProductRequestDTO productDTO = new ProductRequestDTO();
        productDTO.setCode("P999");
        productDTO.setCategoryId(999L);

        Mockito.when(productRepository.findByCode("P999")).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

      
        assertThrows(EntityNotFoundException.class, () -> productService.saveProduct(productDTO));
    }
}
