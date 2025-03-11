package com.otcp.accounting.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductResponseDTO {
    String productName;
    String productCode;
    String description;
    String price;
}
