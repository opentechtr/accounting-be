package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.FilterDTO;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.common.utils.PaginationUtils;
import com.otcp.accounting.product.dto.request.UpdateStockDTO;
import com.otcp.accounting.product.dto.response.StockResponseDTO;
import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.entity.Stock;
import com.otcp.accounting.product.entity.Warehouse;
import com.otcp.accounting.product.repository.StockRepository;
import com.otcp.accounting.product.service.ProductService;
import com.otcp.accounting.product.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.otcp.accounting.product.service.impl.StockTestProvider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ProductService productService;

    @Mock
    private WarehouseService warehouseService;


    @Test
    void test_updateStock_successful() {
        Stock existingStock = getStock();
        Product newProduct = getProduct();
        Warehouse newWarehouse = getWarehouse();

        UpdateStockDTO updateStockDTO = new UpdateStockDTO();
        updateStockDTO.setId(existingStock.getId());
        updateStockDTO.setQuantity(500);
        updateStockDTO.setProductId(newProduct.getId());
        updateStockDTO.setWarehouseId(newWarehouse.getId());

        when(stockRepository.findById(existingStock.getId())).thenReturn(Optional.of(existingStock));
        when(productService.getProductById(newProduct.getId())).thenReturn(newProduct);
        when(warehouseService.findWarehouseById(newWarehouse.getId())).thenReturn(newWarehouse);
        when(stockRepository.save(any(Stock.class))).thenReturn(existingStock);

        StockResponseDTO result = stockService.updateStock(updateStockDTO);

        assertNotNull(result);
        assertEquals(EntityStatus.ACTIVE, existingStock.getEntityStatus());
        assertEquals(500, existingStock.getQuantity());
        verify(stockRepository, times(1)).save(existingStock);
    }

    @Test
    void test_updateStock_deletedStock_shouldThrowException() {
        Stock deletedStock = getStock();
        deletedStock.setEntityStatus(EntityStatus.DELETED);

        UpdateStockDTO updateStockDTO = new UpdateStockDTO();
        updateStockDTO.setId(deletedStock.getId());
        updateStockDTO.setQuantity(300);

        when(stockRepository.getById(deletedStock.getId())).thenReturn(deletedStock);

        assertThrows(EntityNotFoundException.class, () -> stockService.updateStock(updateStockDTO));
        verify(stockRepository, never()).save(any());
    }

    @Test
    void test_getAllStock_successful() {
        FilterDTO filterDTO = createValidFilterDTO();
        Pageable pageable = PaginationUtils.buildPagination(filterDTO);
        Page<Stock> emptyPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(stockRepository.findAll(pageable)).thenReturn(emptyPage);
        Page<Stock> result = stockService.getAllStocks(filterDTO);

        assertTrue(result.getContent().isEmpty());
        assertEquals(0, result.getTotalElements());
        verify(stockRepository).findAll(pageable);
    }

    @Test
    void testGetAllStocks_ReturnsNoContentWhenEmpty() {
        FilterDTO filterDTO = new FilterDTO().setCurrentPage(0).setPageSize(10).setAsc(true);
        Page<Stock> emptyPage = new PageImpl<>(List.of());

        when(stockService.getAllStocks(filterDTO)).thenReturn(emptyPage);

        Page<Stock> response = stockService.getAllStocks(filterDTO);

        assertTrue(response.getContent().isEmpty());
        assertEquals(0, response.getTotalElements());
    }
}
