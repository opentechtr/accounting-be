package com.otcp.accounting.product.controller;

import com.otcp.accounting.common.response.ApiResponse;
import com.otcp.accounting.product.dto.request.WarehouseRequestDTO;
import com.otcp.accounting.product.dto.response.WarehouseResponseDTO;
import com.otcp.accounting.product.entity.Warehouse;
import com.otcp.accounting.product.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ApiResponse<WarehouseResponseDTO> createWarehouse(@Valid @RequestBody WarehouseRequestDTO warehouseDTO) {
        return ApiResponse.success(warehouseService.saveWarehouse(warehouseDTO));
    }

    @GetMapping("/{id}")
    public ApiResponse<WarehouseResponseDTO> getWarehouseById(@PathVariable Long id) {
        return ApiResponse.success(warehouseService.getWarehouseById(id));
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

    @PutMapping("/{id}")
    public ApiResponse<WarehouseResponseDTO> updateWarehouse(@Valid @RequestBody WarehouseRequestDTO warehouseRequestDTO, @PathVariable Long id) {
        return ApiResponse.success(warehouseService.updateWarehouse(id, warehouseRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
