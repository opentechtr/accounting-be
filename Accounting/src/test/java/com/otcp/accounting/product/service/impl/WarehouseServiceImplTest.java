package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.exception.BadRequestException;
import com.otcp.accounting.common.exception.EntityConflictException;
import com.otcp.accounting.common.exception.EntityNotFoundException;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WarehouseServiceImplTest {

    @InjectMocks
    private WarehouseServiceImpl warehouseServiceImpl;

    @Mock
    private WarehouseRepository warehouseRepository;

    void test_saveWarehouse_successful() {
        WarehouseRequestDTO warehouseDTO = new WarehouseRequestDTO("New Warehouse", "New Location");
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDTO.getName());
        warehouse.setLocation(warehouseDTO.getLocation());

        when(warehouseRepository.existsByName(warehouseDTO.getName())).thenReturn(false);
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);

        WarehouseResponseDTO savedWarehouse = warehouseServiceImpl.saveWarehouse(warehouseDTO);

        assertNotNull(savedWarehouse);
        assertEquals(warehouse.getName(), savedWarehouse.getName());
        assertEquals(warehouse.getLocation(), savedWarehouse.getLocation());
    }

    @Test
    void test_saveWarehouse_nameConflictShouldThrowException() {
        WarehouseRequestDTO warehouseDTO = new WarehouseRequestDTO("Existing Name", "Valid Location");

        when(warehouseRepository.existsByName(warehouseDTO.getName())).thenReturn(true);

        assertThrows(EntityConflictException.class, () -> warehouseServiceImpl.saveWarehouse(warehouseDTO));

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
        when(warehouseRepository.getById(id)).thenCallRealMethod();
        when(warehouseRepository.existsByNameAndIdNot(updatedWarehouseDTO.getName(), id)).thenReturn(false);
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(existingWarehouse);

        WarehouseResponseDTO result = warehouseServiceImpl.updateWarehouse(id, updatedWarehouseDTO);

        assertEquals("Updated Name", result.getName());
        assertEquals("Updated Location", result.getLocation());
    }

    @Test
    void test_updateWarehouse_nameConflictShouldThrowException() {
        Long id = 1L;

        Warehouse existingWarehouse = new Warehouse();
        existingWarehouse.setId(id);
        existingWarehouse.setName("Old Name");
        existingWarehouse.setLocation("Old Location");

        WarehouseRequestDTO updatedWarehouseDTO = new WarehouseRequestDTO("Duplicate Name", "Valid Location");

        // findById getById için gereklidir
        when(warehouseRepository.findById(id)).thenReturn(Optional.of(existingWarehouse));
        when(warehouseRepository.existsById(id)).thenReturn(true);
        when(warehouseRepository.existsByNameAndIdNot(updatedWarehouseDTO.getName(), id)).thenReturn(true);

        // getById default metot olduğu için gerçek metodu çağırmalıyız
        when(warehouseRepository.getById(id)).thenCallRealMethod();

        assertThrows(EntityConflictException.class,
                () -> warehouseServiceImpl.updateWarehouse(id, updatedWarehouseDTO));

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

        WarehouseRequestDTO updatedWarehouseDTO = new WarehouseRequestDTO("", "Valid Location");

        when(warehouseRepository.existsById(id)).thenReturn(true);
        when(warehouseRepository.findById(id)).thenReturn(Optional.of(existingWarehouse));

        assertThrows(BadRequestException.class, () -> warehouseServiceImpl.updateWarehouse(id, updatedWarehouseDTO));
    }

    @Test
    void test_updateWarehouse_emptyLocationShouldThrowException() {
        Long id = 1L;
        Warehouse existingWarehouse = new Warehouse();
        existingWarehouse.setName("Old Name");

        WarehouseRequestDTO updatedWarehouseDTO = new WarehouseRequestDTO("Valid Name", "");

        when(warehouseRepository.existsById(id)).thenReturn(true);
        when(warehouseRepository.findById(id)).thenReturn(Optional.of(existingWarehouse));

        assertThrows(BadRequestException.class, () -> warehouseServiceImpl.updateWarehouse(id, updatedWarehouseDTO));
    }

    @Test
    void test_getWarehouseById_successful() {
        Long warehouseId = 1L;
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Existing Warehouse");
        warehouse.setLocation("Existing Location");

        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.of(warehouse));

        WarehouseResponseDTO response = warehouseServiceImpl.getWarehouseById(warehouseId);

        assertEquals(warehouse.getName(), response.getName());
        assertEquals(warehouse.getLocation(), response.getLocation());
    }

    // **8️⃣ Depo ID ile Arama Yapıldığında Bulunamazsa Exception Fırlatma Testi**
    @Test
    void test_getWarehouseById_notFound_shouldThrowException() {
        Long warehouseId = 1L;

        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> warehouseServiceImpl.getWarehouseById(warehouseId));
    }
}