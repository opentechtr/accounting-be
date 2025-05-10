package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.FilterDTO;
import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.entity.Stock;
import com.otcp.accounting.product.entity.Warehouse;

public class StockTestProvider {

    public static Stock getStock() {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setQuantity(100);
        stock.setEntityStatus(EntityStatus.ACTIVE);
        stock.setProduct(getProduct());
        stock.setWarehouse(getWarehouse());
        return stock;
    }

    public static Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setCode("P123");
        return product;
    }

    public static Warehouse getWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1L);
        warehouse.setName("Main Warehouse");
        warehouse.setLocation("Istanbul");
        return warehouse;
    }

    public static FilterDTO createValidFilterDTO() {
        return new FilterDTO()
                .setCurrentPage(0)
                .setPageSize(10)
                .setSortingColumn("")
                .setAsc(true);
    }
}
