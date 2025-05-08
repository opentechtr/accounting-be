package com.otcp.accounting.product.service;

import com.otcp.accounting.product.dto.request.CreateWarehouseDTO;
import com.otcp.accounting.product.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse saveWarehouse(CreateWarehouseDTO warehouseDTO);
    Warehouse getWarehouseById(Long id);
    List<Warehouse> getAllWarehouses();
    Warehouse updateWarehouse(Long id, Warehouse warehouse);
    void deleteWarehouse(Long id);
    Warehouse findWarehouseById(Long id);
}
