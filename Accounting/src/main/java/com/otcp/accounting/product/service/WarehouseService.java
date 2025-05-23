package com.otcp.accounting.product.service;

import com.otcp.accounting.product.dto.request.WarehouseRequestDTO;
import com.otcp.accounting.product.dto.response.WarehouseResponseDTO;
import com.otcp.accounting.product.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    WarehouseResponseDTO saveWarehouse(WarehouseRequestDTO warehouseDTO);
    WarehouseResponseDTO getWarehouseById(Long id);
    List<Warehouse> getAllWarehouses();
    WarehouseResponseDTO updateWarehouse(Long id, WarehouseRequestDTO warehouseRequestDTO);
    void deleteWarehouse(Long id);
    Warehouse findWarehouseById(Long id);
}
