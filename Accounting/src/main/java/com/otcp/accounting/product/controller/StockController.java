package com.otcp.accounting.product.controller;

import com.otcp.accounting.common.dto.FilterDTO;
import com.otcp.accounting.common.response.ApiResponse;
import com.otcp.accounting.product.dto.request.UpdateStockDTO;
import com.otcp.accounting.product.dto.response.StockResponseDTO;
import com.otcp.accounting.product.entity.Stock;
import com.otcp.accounting.product.service.StockService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/create")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        return new ResponseEntity<>(stockService.saveStock(stock), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @PostMapping
    public ApiResponse<Page<Stock>> getAllStocks(@Valid @RequestBody FilterDTO filterDTO) {
        Page<Stock> stocks = stockService.getAllStocks(filterDTO);

        if (stocks.getTotalElements() == 0) {
            return ApiResponse.noContent();
        }

        return ApiResponse.success(stockService.getAllStocks(filterDTO));
    }

    @PutMapping
    public ApiResponse<StockResponseDTO> updateStock(@Valid @RequestBody UpdateStockDTO updateStockDTO) {
        StockResponseDTO stockResponseDTO = stockService.updateStock(updateStockDTO);
        return ApiResponse.success(stockResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
