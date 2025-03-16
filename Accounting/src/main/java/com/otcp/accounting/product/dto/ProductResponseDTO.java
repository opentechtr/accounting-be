package com.otcp.accounting.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDTO {

        @NotBlank(message = "Product name is mandatory")
        private String name;

        @NotBlank(message = "Product code is mandatory")
        private String code;

        private String description;
        @NotNull(message = "Price is mandatory")
        @Positive(message = "Price must be positive")
        private BigDecimal price;

        @NotNull(message = "Category ID is mandatory")
        private Long categoryId;
    }


