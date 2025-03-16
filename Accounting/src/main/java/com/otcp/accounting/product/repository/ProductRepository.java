package com.otcp.accounting.product.repository;

import com.otcp.accounting.common.base.BaseRepository;
import com.otcp.accounting.product.entity.Product;

import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product, Long> {

    Optional<Product> findByCode(String code);
}