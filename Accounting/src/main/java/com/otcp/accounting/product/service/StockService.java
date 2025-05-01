package com.otcp.accounting.product.service;

import com.otcp.accounting.product.dto.request.UpdateStockDTO;
import com.otcp.accounting.product.dto.response.StockResponseDTO;
import com.otcp.accounting.product.entity.Stock;

import java.util.List;

public interface StockService {
    Stock saveStock(Stock stock);
    Stock getStockById(Long id);
    List<Stock> getAllStocks();
    StockResponseDTO updateStock(UpdateStockDTO updateStockDTO);
    void deleteStock(Long id);
}
