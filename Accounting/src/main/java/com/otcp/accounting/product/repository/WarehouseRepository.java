package com.otcp.accounting.product.repository;

import com.otcp.accounting.common.base.BaseRepository;
import com.otcp.accounting.product.entity.Warehouse;

public interface WarehouseRepository extends BaseRepository<Warehouse, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name,Long id);
}