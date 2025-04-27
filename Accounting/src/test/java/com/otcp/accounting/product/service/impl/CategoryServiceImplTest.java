package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.exception.EntityConflictEexception;
import com.otcp.accounting.product.dto.request.UpdateCategoryDTO;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.response.CategoryResponseDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static com.otcp.accounting.product.service.impl.CategoryTestProvider.getCategory;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void test_updateCategory_successful() {
        Long categoryId = 1L;
        Category existingCategory = getCategory();
        existingCategory.setId(categoryId);
        String newCategoryName = "Updated Category Name";
        String newDescription = "Updated description";

        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setId(categoryId);
        updateCategoryDTO.setName(newCategoryName);
        updateCategoryDTO.setDescription(newDescription);

        when(categoryRepository.findById(categoryId)).thenReturn(java.util.Optional.of(existingCategory));
        when(categoryRepository.existsByName(newCategoryName)).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        Category updatedCategory = categoryServiceImpl.updateCategory(updateCategoryDTO);

        assertNotNull(updatedCategory);
        assertEquals(newCategoryName, updatedCategory.getName());
        assertEquals(newDescription, updatedCategory.getDescription());

        verify(categoryRepository, times(1)).existsByName(newCategoryName);
        verify(categoryRepository, times(1)).save(updatedCategory);
    }

    @Test
    void test_updateCategory_nameConflict() {
        Long categoryId = 1L;
        Category existingCategory = getCategory();
        existingCategory.setId(categoryId);
        String conflictingCategoryName = "Existing Category Name";
        String newDescription = "Updated description";

        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setId(categoryId);
        updateCategoryDTO.setName(conflictingCategoryName);
        updateCategoryDTO.setDescription(newDescription);

        when(categoryRepository.findById(categoryId)).thenReturn(java.util.Optional.of(existingCategory));
        when(categoryRepository.existsByName(conflictingCategoryName)).thenReturn(true);

        EntityConflictEexception exception = assertThrows(EntityConflictEexception.class, () -> {
            categoryServiceImpl.updateCategory(updateCategoryDTO);
        });

        assertNotNull(exception);
        assertEquals("5001", exception.getErrorCode());

        verify(categoryRepository, times(1)).existsByName(conflictingCategoryName);
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void test_searchCategoriesByName_successful() {
        String categoryName = "Electronics";
        Category category = getCategory();
        category.setName(categoryName);

        when(categoryRepository.findAllByNameContainingIgnoreCase(categoryName)).thenReturn(List.of(category));

        List<CategoryResponseDTO> result = categoryServiceImpl.searchCategoriesByName(categoryName);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(category.getName(), result.get(0).getName());
    }

    @Test
    void test_searchCategoriesByName_notFound_shouldThrowNotFoundException() {
        String nonExistentcategoryName = "Non Existent Category";

        when(categoryRepository.findAllByNameContainingIgnoreCase(nonExistentcategoryName)).thenReturn(Collections.emptyList());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> categoryServiceImpl.searchCategoriesByName(nonExistentcategoryName));

        assertEquals("5000", exception.getErrorCode());
    }
}
