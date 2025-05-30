package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.exception.EntityConflictException;
import com.otcp.accounting.product.dto.request.CreateCategoryDTO;
import com.otcp.accounting.product.dto.response.CategoryResponseDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static com.otcp.accounting.product.service.impl.CategoryTestProvider.createCategoryRequestDTO;

import static com.otcp.accounting.product.service.impl.CategoryTestProvider.getCategory;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void test_getCategoryById_successful() {
        Long id = 1L;
        Category expectedCategory = getCategory();

        when(categoryRepository.getById(id)).thenReturn(expectedCategory);

        CategoryResponseDTO actualResult = categoryServiceImpl.getCategoryById(id);

        assertNotNull(actualResult);
        assertEquals(expectedCategory.getName(), actualResult.getName());
    }

    @Test
    void createCategory_successful() {
        CreateCategoryDTO categoryDTO = createCategoryRequestDTO();
        Category expectedCategory = getCategory(categoryDTO);

        Mockito.when(categoryRepository.findByName("test category")).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(expectedCategory);

        CategoryResponseDTO actualResult = categoryServiceImpl.saveCategory(categoryDTO);
        assertNotNull(expectedCategory.getName(),actualResult.getName());

    }

    @Test
    void createCategory_ShouldThrowDuplicateException_WhenCategoryNameExists() {
        CreateCategoryDTO categoryDTO = new CreateCategoryDTO();
        categoryDTO.setName("test category");
        categoryDTO.setDescription("test description");

        Mockito.when(categoryRepository.findByName("test category")).thenReturn(Optional.of(new Category()));

        assertThrows(EntityConflictException.class, () -> categoryServiceImpl.saveCategory(categoryDTO));

    }


}
