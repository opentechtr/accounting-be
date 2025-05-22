package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.exception.BadRequestException;
import com.otcp.accounting.common.exception.EntityConflictException;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.request.CreateWarehouseDTO;
import com.otcp.accounting.product.dto.request.WarehouseRequestDTO;
import com.otcp.accounting.product.dto.response.WarehouseResponseDTO;
import com.otcp.accounting.product.entity.Warehouse;
import com.otcp.accounting.product.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

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

        EntityConflictException exception = assertThrows(EntityConflictException.class, () -> {
            warehouseServiceImpl.saveWarehouse(warehouseDTO);
        });

        assertNotNull(exception);
        assertEquals("5001", exception.getErrorCode());

        verify(warehouseRepository, times(1)).existsByName(warehouseDTO.getName());
        verify(warehouseRepository, never()).save(any(Warehouse.class));
    }

    @Test
    void test_updateWarehouse_successful() {
        Long id = 1L;
        Warehouse existingWarehouse = new Warehouse();
        existingWarehouse.setName("Old Name");
        existingWarehouse.setLocation("Old Location");

        WarehouseRequestDTO updatedWarehouseDTO = new WarehouseRequestDTO("Updated Name", "Updated Location");

        when(warehouseRepository.existsById(id)).thenReturn(true);
        when(warehouseRepository.findById(id)).thenReturn(Optional.of(existingWarehouse));
        when(warehouseRepository.existsByNameAndIdNot(updatedWarehouseDTO.getName(), id)).thenReturn(false);
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(existingWarehouse);

        WarehouseResponseDTO result = warehouseServiceImpl.updateWarehouse(id, updatedWarehouseDTO);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals("Updated Location", result.getLocation());

        verify(warehouseRepository, times(1)).existsById(id);
        verify(warehouseRepository, times(1)).findById(id);
        verify(warehouseRepository, times(1)).save(any(Warehouse.class));
    }

    @Test
    void test_updateWarehouse_nameConflictShouldThrowException() {
        Long id = 1L;
        Warehouse existingWarehouse = new Warehouse();
        existingWarehouse.setName("Old Name");
        existingWarehouse.setLocation("Old Location");

        WarehouseRequestDTO updatedWarehouseDTO = new WarehouseRequestDTO("Duplicate Name", "Valid Location");

        when(warehouseRepository.existsById(id)).thenReturn(true);
        when(warehouseRepository.findById(id)).thenReturn(Optional.of(existingWarehouse));
        when(warehouseRepository.existsByNameAndIdNot(updatedWarehouseDTO.getName(), id)).thenReturn(true);

        assertThrows(EntityConflictException.class, () -> warehouseServiceImpl.updateWarehouse(id, updatedWarehouseDTO));

        verify(warehouseRepository, times(1)).existsById(id);
        verify(warehouseRepository, times(1)).findById(id);
        verify(warehouseRepository, times(1)).existsByNameAndIdNot(updatedWarehouseDTO.getName(), id);
        verify(warehouseRepository, never()).save(any(Warehouse.class));
    }



    @Test
    void test_updateWarehouse_emptyNameShouldThrowException() {
        Long id = 1L;
        Warehouse existingWarehouse = new Warehouse();
        existingWarehouse.setName("Old Name");
        existingWarehouse.setLocation("Old Location");

        WarehouseRequestDTO updatedWarehouseDTO = new WarehouseRequestDTO("", "Valid Location");

        when(warehouseRepository.existsById(id)).thenReturn(true);
        when(warehouseRepository.findById(id)).thenReturn(Optional.of(existingWarehouse));

        assertThrows(BadRequestException.class, () -> warehouseServiceImpl.updateWarehouse(id, updatedWarehouseDTO));

        verify(warehouseRepository, times(1)).existsById(id);
        verify(warehouseRepository, times(1)).findById(id);
        verify(warehouseRepository, never()).save(any(Warehouse.class));
    }

    @Test
    void test_updateWarehouse_emptyLocationShouldThrowException() {
        Long id = 1L;
        Warehouse existingWarehouse = new Warehouse();
        existingWarehouse.setName("Old Name");
        existingWarehouse.setLocation("Old Location");

        WarehouseRequestDTO updatedWarehouseDTO = new WarehouseRequestDTO("Valid Name", "");

        when(warehouseRepository.existsById(id)).thenReturn(true);
        when(warehouseRepository.findById(id)).thenReturn(Optional.of(existingWarehouse));

        assertThrows(BadRequestException.class, () -> warehouseServiceImpl.updateWarehouse(id, updatedWarehouseDTO));

        verify(warehouseRepository, times(1)).existsById(id);
        verify(warehouseRepository, times(1)).findById(id);
        verify(warehouseRepository, never()).save(any(Warehouse.class));
    }
}
