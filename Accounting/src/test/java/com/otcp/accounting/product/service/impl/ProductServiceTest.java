package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.exception.EntityConflictException;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.request.ProductRequestDTO;
import com.otcp.accounting.product.dto.request.ProductUpdateDTO;
import com.otcp.accounting.product.dto.response.ProductResponseDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.repository.CategoryRepository;
import com.otcp.accounting.product.repository.ProductRepository;
import com.otcp.accounting.product.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.List;
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

    @Mock
    private CategoryService categoryService;

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


        assertThrows(EntityConflictException.class, () -> productService.saveProduct(productDTO));
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

    @Test
    void getProductsByCategory_ShouldReturnProductList_WhenProductsExist() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setCategory(category);

        List<Product> productList = List.of(product1, product2);

        Mockito.when(productRepository.findAllByCategory_Id(categoryId)).thenReturn(productList);

        List<ProductResponseDTO> result = productService.getProductsByCategory(categoryId);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getProductsByCategory_ShouldThrowEntityNotFoundException_WhenNoProductsExist() {
        Long categoryId = 1L;

        Mockito.when(productRepository.findAllByCategory_Id(categoryId)).thenReturn(List.of());

        assertThrows(EntityNotFoundException.class, () -> productService.getProductsByCategory(categoryId));
    }

    @Test
    void updateProduct_ShouldUpdateSuccessfully_WhenCodeUnchanged() {
        ProductUpdateDTO updateDTO = new ProductUpdateDTO();
        updateDTO.setId(1L);
        updateDTO.setCode("P123");
        updateDTO.setName("Updated Product");
        updateDTO.setDescription("Updated description");
        updateDTO.setPrice(BigDecimal.valueOf(150.0));
        updateDTO.setCategoryId(1L);

        Product existingProduct = getProduct(createProductRequestDTO(), getCategory());
        existingProduct.setId(1L);

        Category category = getCategory();

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        Mockito.when(categoryService.getCategory(1L)).thenReturn(category);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenAnswer(inv -> inv.getArgument(0));

        ProductResponseDTO result = productService.updateProduct(updateDTO);

        assertNotNull(result);
        assertEquals("P123", result.getCode());
        assertEquals("Updated Product", result.getName());
    }

    @Test
    void updateProduct_ShouldUpdateSuccessfully_WhenCodeChanged() {
        String code = "P999";
        ProductUpdateDTO updateDTO = new ProductUpdateDTO();
        updateDTO.setId(1L);
        updateDTO.setCode(code);
        updateDTO.setName("Updated Product");
        updateDTO.setDescription("Updated description");
        updateDTO.setPrice(BigDecimal.valueOf(200.0));
        updateDTO.setCategoryId(1L);

        Product existingProduct = getProduct(createProductRequestDTO(), getCategory());
        existingProduct.setId(1L);

        Category category = getCategory();

        Mockito.when(productRepository.findById(updateDTO.getId())).thenReturn(Optional.of(existingProduct));
        Mockito.when(productRepository.findByCode(updateDTO.getCode())).thenReturn(Optional.empty());
        Mockito.when(categoryService.getCategory(updateDTO.getCategoryId())).thenReturn(category);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenAnswer(inv -> inv.getArgument(0));

        ProductResponseDTO result = productService.updateProduct(updateDTO);

        assertNotNull(result);
        assertEquals(updateDTO.getCode(), result.getCode());
        assertEquals(updateDTO.getName(), result.getName());
    }

    @Test
    void updateProduct_ShouldThrowConflictException_WhenNewCodeExistsInOtherProduct() {
        String newCode = "P999";
        Long updatingProductId = 1L;
        Long conflictingProductId = 2L;

        ProductUpdateDTO updateDTO = new ProductUpdateDTO();
        updateDTO.setId(updatingProductId);
        updateDTO.setCode(newCode);
        updateDTO.setName("Updated Name");
        updateDTO.setDescription("Updated Description");
        updateDTO.setPrice(BigDecimal.valueOf(200.0));
        updateDTO.setCategoryId(1L);

        Product existingProduct = getProduct(createProductRequestDTO(), getCategory());
        existingProduct.setId(updatingProductId);

        Product anotherProductWithSameCode = new Product();
        anotherProductWithSameCode.setId(conflictingProductId);
        anotherProductWithSameCode.setCode(newCode);

        Mockito.when(productRepository.findById(updatingProductId)).thenReturn(Optional.of(existingProduct));
        Mockito.when(productRepository.findByCode(newCode)).thenReturn(Optional.of(anotherProductWithSameCode));

        assertThrows(EntityConflictException.class, () -> productService.updateProduct(updateDTO));
    }

    @Test
    void updateProduct_ShouldThrowEntityNotFoundException_WhenProductNotFound() {
        ProductUpdateDTO updateDTO = new ProductUpdateDTO();
        updateDTO.setId(999L);

        Mockito.when(productRepository.findById(updateDTO.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(updateDTO));
    }


    @Test
    void deleteProduct_ShouldMarkProductAsDeleted_WhenProductExistsAndActive() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setEntityStatus(EntityStatus.ACTIVE);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        assertDoesNotThrow(() -> productService.deleteProduct(productId));
        assertEquals(EntityStatus.DELETED, product.getEntityStatus());
        Mockito.verify(productRepository).save(product);
    }

    @Test
    void deleteProduct_ShouldThrowException_WhenProductAlreadyDeleted() {
        Long productId = 2L;
        Product product = new Product();
        product.setId(productId);
        product.setEntityStatus(EntityStatus.DELETED);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertThrows(EntityNotFoundException.class, () -> productService.deleteProduct(productId));
        Mockito.verify(productRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deleteProduct_ShouldThrowException_WhenProductNotFound() {
        Long productId = 3L;

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.deleteProduct(productId));
        Mockito.verify(productRepository, Mockito.never()).save(Mockito.any());
    }

}
