package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.entity.Stock;
import com.otcp.accounting.product.repository.StockRepository;
import com.otcp.accounting.product.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Stock saveStock(Stock stock) {
        return null;
    }

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Stock> getAllStocks() {
        return null;
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        return null;
    }

    @Override
    public void deleteStock(Long id) {
        Stock stock = getStockById(id);
        stockRepository.delete(stock);
    }
}
