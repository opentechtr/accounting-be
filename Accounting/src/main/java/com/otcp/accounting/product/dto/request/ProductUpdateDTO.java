package com.otcp.accounting.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductUpdateDTO {

    @NotNull(message = "Product ID is mandatory")
    private Long id;

    @NotBlank(message = "Product name is mandatory")
    @Size(min = 3, max = 100, message = "Product name must be at most 100 characters.")
    private String name;

    @NotBlank(message = "Product code is mandatory")
    @Size(min = 1, max = 50, message = "Product code must be at most 50 characters.")
    private String code;

    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;
}
