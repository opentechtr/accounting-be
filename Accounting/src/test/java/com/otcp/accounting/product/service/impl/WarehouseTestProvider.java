package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.product.dto.request.CreateWarehouseDTO;
import com.otcp.accounting.product.entity.Warehouse;

public class WarehouseTestProvider {

    public static CreateWarehouseDTO createWarehouseRequestDTO() {
        CreateWarehouseDTO warehouseDTO = new CreateWarehouseDTO();
        warehouseDTO.setName("Test Warehouse");
        warehouseDTO.setLocation("Test Location");
        return warehouseDTO;
    }

    public static Warehouse getWarehouse(CreateWarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1L);
        warehouse.setName(warehouseDTO.getName());
        warehouse.setLocation(warehouseDTO.getLocation());
        return warehouse;
    }
}
