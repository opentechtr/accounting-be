
package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.DtoConverter;
import com.otcp.accounting.common.exception.DuplicateException;
import com.otcp.accounting.common.exception.EntityNotFoundException;

import com.otcp.accounting.product.dto.ProductResponseDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.repository.CategoryRepository;
import com.otcp.accounting.product.repository.ProductRepository;
import com.otcp.accounting.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ProductResponseDTO saveProduct(ProductResponseDTO productResponseDTO)  {

        if (productRepository.findByCode(productResponseDTO.getCode()).isPresent()) {
            throw new DuplicateException();
        }
        Category category = categoryRepository.findById(productResponseDTO.getCategoryId())
                .orElseThrow(EntityNotFoundException::new);


        Product product ;
        product = DtoConverter.convert(productResponseDTO,Product.class);
        product.setCategory(category);
         productRepository.save(product);
         return DtoConverter.convert(product,ProductResponseDTO.class);


    }

    @Override
    public ProductResponseDTO getProductResponseDtoById(Long id) {
        Product product = productRepository.findByIdAndEntityStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(EntityNotFoundException::new);
        return DtoConverter.convert(product, ProductResponseDTO.class);
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
