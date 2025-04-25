package com.otcp.accounting.product.repository;

import com.otcp.accounting.common.base.BaseRepository;
import com.otcp.accounting.product.entity.Category;

public interface CategoryRepository extends BaseRepository<Category, Long> {
    boolean existsByName(String name);
}