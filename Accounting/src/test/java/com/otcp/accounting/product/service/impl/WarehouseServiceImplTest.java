package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.exception.EntityConflictEexception;
import com.otcp.accounting.product.dto.request.CreateWarehouseDTO;
import com.otcp.accounting.product.entity.Warehouse;
import com.otcp.accounting.product.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;

import static com.otcp.accounting.product.service.impl.WarehouseTestProvider.createWarehouseRequestDTO;
import static com.otcp.accounting.product.service.impl.WarehouseTestProvider.getWarehouse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WarehouseServiceImplTest {

    @InjectMocks
    private WarehouseServiceImpl warehouseServiceImpl;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Test
    void test_saveWarehouse_successful(){
        CreateWarehouseDTO warehouseDTO = createWarehouseRequestDTO();
        Warehouse warehouse = getWarehouse(warehouseDTO);

        when(warehouseRepository.existsByName(warehouseDTO.getName())).thenReturn(false);
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);

        Warehouse savedWarehouse = warehouseServiceImpl.saveWarehouse(warehouseDTO);

        assertNotNull(savedWarehouse);
        assertEquals(warehouse.getName(), savedWarehouse.getName());
        assertEquals(warehouse.getLocation(), savedWarehouse.getLocation());

        verify(warehouseRepository, times(1)).existsByName(warehouseDTO.getName());
        verify(warehouseRepository, times(1)).save(any(Warehouse.class));
    }

    @Test
    void test_saveWarehouse_nameConflictShouldThrowException(){
        CreateWarehouseDTO warehouseDTO = createWarehouseRequestDTO();

        when(warehouseRepository.existsByName(warehouseDTO.getName())).thenReturn(true);

        EntityConflictEexception exception = assertThrows(EntityConflictEexception.class, () -> {
            warehouseServiceImpl.saveWarehouse(warehouseDTO);
        });

        assertNotNull(exception);
        assertEquals("5001", exception.getErrorCode());

        verify(warehouseRepository, times(1)).existsByName(warehouseDTO.getName());
        verify(warehouseRepository, never()).save(any(Warehouse.class));
    }
}
