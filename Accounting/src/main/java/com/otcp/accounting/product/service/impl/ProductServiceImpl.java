
package com.otcp.accounting.product.service.impl;

import com.otcp.accounting.common.base.EntityStatus;
import com.otcp.accounting.common.dto.DtoConverter;
import com.otcp.accounting.common.exception.EntityConflictEexception;
import com.otcp.accounting.common.exception.EntityNotFoundException;

import com.otcp.accounting.product.dto.request.ProductRequestDTO;
import com.otcp.accounting.product.dto.request.ProductUpdateDTO;
import com.otcp.accounting.product.dto.response.ProductResponseDTO;
import com.otcp.accounting.product.entity.Category;
import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.repository.ProductRepository;
import com.otcp.accounting.product.service.CategoryService;
import com.otcp.accounting.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Override
    public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO)  {

        if (productRepository.findByCode(productRequestDTO.getCode()).isPresent()) {
            throw new EntityConflictEexception();
        }
        Category category = categoryService.getCategory(productRequestDTO.getCategoryId());

        Product product ;
        product = DtoConverter.convert(productRequestDTO,Product.class);
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
    public List<ProductResponseDTO> getProductsByCategory(Long categoryId) {
        List<Product> productList = productRepository.findAllByCategory_Id(categoryId);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return DtoConverter.convertList(productList,ProductResponseDTO.class);
    }

    @Override
    public ProductResponseDTO updateProduct(ProductUpdateDTO productUpdateDTO) {
        Product product = getProductById(productUpdateDTO.getId());
        boolean isCodeChanged = !product.getCode().equals(productUpdateDTO.getCode());

        if (isCodeChanged) {
            productRepository.findByCode(productUpdateDTO.getCode())
                    .filter(p -> !p.getId().equals(productUpdateDTO.getId()))
                    .ifPresent(p -> {
                        throw new EntityConflictEexception();
                    });
            product.setCode(productUpdateDTO.getCode());
        }

        if (StringUtils.hasText(productUpdateDTO.getDescription())) {
            product.setDescription(productUpdateDTO.getDescription());
        }

        product.setName(productUpdateDTO.getName());
        product.setPrice(productUpdateDTO.getPrice());
        product.setCategory(categoryService.getCategory(productUpdateDTO.getCategoryId()));

        productRepository.save(product);

        return DtoConverter.convert(product, ProductResponseDTO.class);
    }

    private Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProduct(id);

        if (product.getEntityStatus() == EntityStatus.DELETED) {
            throw new EntityNotFoundException();
        }

        product.delete();
        productRepository.save(product);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return null;
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
