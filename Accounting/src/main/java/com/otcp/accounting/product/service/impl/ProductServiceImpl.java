
package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.DtoConverter;
import com.otcp.accounting.common.exception.EntityNotFoundException;
import com.otcp.accounting.product.dto.ProductResponseDTO;
import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.repository.ProductRepository;
import com.otcp.accounting.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return DtoConverter.convert(productRepository.findByIdAndEntityStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(EntityNotFoundException::new), ProductResponseDTO.class);
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return null;
    }
}
