package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.DtoConverter;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.request.UpdateStockDTO;
import com.otcp.accounting.product.dto.response.StockResponseDTO;
import com.otcp.accounting.product.entity.Stock;
import com.otcp.accounting.product.repository.StockRepository;
import com.otcp.accounting.product.service.ProductService;
import com.otcp.accounting.product.service.StockService;
import com.otcp.accounting.product.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    private final ProductService productService;

    private final WarehouseService warehouseService;

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
    public StockResponseDTO updateStock(UpdateStockDTO updateStockDTO) {
        Stock stock = getStockById(updateStockDTO.getId());

        if (stock.getEntityStatus() == EntityStatus.DELETED) {
            throw new EntityNotFoundException();
        }

        stock.setQuantity(updateStockDTO.getQuantity());
        stock.setEntityStatus(EntityStatus.ACTIVE);

        if (updateStockDTO.getProductId() != null) {
            stock.setProduct(productService.getProductById(updateStockDTO.getProductId()));
        }

        if (updateStockDTO.getWarehouseId() != null) {
            stock.setWarehouse(warehouseService.getWarehouseById(updateStockDTO.getWarehouseId()));
        }

        stockRepository.save(stock);

        return DtoConverter.convert(stock, StockResponseDTO.class);
    }

    @Override
    public void deleteStock(Long id) {

    }
}
