package com.otcp.accounting.product.repository;

import com.otcp.accounting.common.base.BaseRepository;
import com.otcp.accounting.product.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category, Long> {
    Optional<Category> findByName(String name);
}