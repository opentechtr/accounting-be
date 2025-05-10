package com.otcp.accounting.product.service;

import com.otcp.accounting.common.dto.FilterDTO;
import com.otcp.accounting.product.dto.request.UpdateStockDTO;
import com.otcp.accounting.product.dto.response.StockResponseDTO;
import com.otcp.accounting.product.entity.Stock;
import org.springframework.data.domain.Page;


public interface StockService {
    Stock saveStock(Stock stock);
    Stock getStockById(Long id);
    Page<Stock> getAllStocks(FilterDTO filterDTO);
    StockResponseDTO updateStock(UpdateStockDTO updateStockDTO);
    void deleteStock(Long id);
}
