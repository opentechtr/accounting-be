package com.otcp.accounting.product.repository;

import com.otcp.accounting.common.base.BaseRepository;
import com.otcp.accounting.product.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StockRepository extends BaseRepository<Stock, Long> {
    Page<Stock> findAll(Pageable pageable);
}