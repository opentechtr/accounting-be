package com.otcp.accounting.product.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStockDTO {

    @NotNull(message = "Product ID is mandatory")
    private Long id;

    @Min(value = 1, message = "Quantity should be bigger than 0")
    private int quantity;

    private Long productId;

    private Long warehouseId;
}
