package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.DtoConverter;
import com.otcp.accounting.common.exception.BadRequestException;
import com.otcp.accounting.common.exception.EntityConflictException;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.request.CreateWarehouseDTO;
import com.otcp.accounting.product.entity.Warehouse;
import com.otcp.accounting.product.repository.WarehouseRepository;
import com.otcp.accounting.product.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse saveWarehouse(CreateWarehouseDTO warehouseDTO) {

        if (warehouseRepository.existsByName(warehouseDTO.getName()))
            throw new EntityConflictException();

        Warehouse warehouse = DtoConverter.convert(warehouseDTO, Warehouse.class);
        warehouse.setEntityStatus(EntityStatus.ACTIVE);

        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse getWarehouseById(Long id) {
        return null;
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return null;
    }


    @Override
    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        logger.info("Attempting to update warehouse with ID: {}", id);

        if (!warehouseRepository.existsById(id)) {
            logger.error("Warehouse with ID: {} not found (Error Code: {})", id, "5000");
            throw new EntityNotFoundException();
        }

        Warehouse existingWarehouse = warehouseRepository.getById(id);

        if (!StringUtils.hasText(warehouse.getName())) {
            logger.error("Warehouse name cannot be empty (Error Code: {})", "4001");
            throw new BadRequestException("4001");
        }

        if (!StringUtils.hasText(warehouse.getLocation())) {
            logger.error("Warehouse location cannot be empty (Error Code: {})", "4002");
            throw new BadRequestException("4002");
        }

        if (!existingWarehouse.getName().equals(warehouse.getName()) &&
                warehouseRepository.existsByNameAndIdNot(warehouse.getName(), id)) {
            logger.error("Warehouse name '{}' is already in use (Error Code: {})", warehouse.getName(), "5001");
            throw new EntityConflictException();
            //TODO: Burada aynı mantıkla, hata kodlarını doğru şekilde yönetmek için EntityConflictException Warehouse icin ozellestirilebilir.

        }

        existingWarehouse.setName(warehouse.getName());
        existingWarehouse.setLocation(warehouse.getLocation());

        Warehouse updatedWarehouse = warehouseRepository.save(existingWarehouse);
        logger.info("Successfully updated warehouse with ID: {} ", id);

        return updatedWarehouse;
    }


    @Override
    public void deleteWarehouse(Long id) {

    }
}
