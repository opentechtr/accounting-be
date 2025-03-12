package com.otcp.accounting.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDTO {
    String name;
    String code;
    String description;
    BigDecimal price;
}
