package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.DtoConverter;
import com.otcp.accounting.common.exception.EntityConflictEexception;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.request.CreateWarehouseDTO;
import com.otcp.accounting.product.dto.response.WarehouseResponseDTO;
import com.otcp.accounting.product.entity.Warehouse;
import com.otcp.accounting.product.repository.WarehouseRepository;
import com.otcp.accounting.product.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse saveWarehouse(CreateWarehouseDTO warehouseDTO) {
        if (existsByName(warehouseDTO.getName())) {
            throw new EntityConflictEexception();
        }

        Warehouse warehouse = DtoConverter.convert(warehouseDTO, Warehouse.class);
        warehouse.setEntityStatus(EntityStatus.ACTIVE);

        return warehouseRepository.save(warehouse);
    }

    @Override
    public WarehouseResponseDTO getWarehouseById(Long id) {
        Warehouse warehouse = findWarehouseById(id);

        return DtoConverter.convert(warehouse, WarehouseResponseDTO.class);
    }

    @Override
    public Warehouse findWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return null;
    }

    @Override
    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        return null;
    }

    @Override
    public void deleteWarehouse(Long id) {

    }

    private boolean existsByName(String name) {
        return warehouseRepository.existsByName(name);
    }
}
