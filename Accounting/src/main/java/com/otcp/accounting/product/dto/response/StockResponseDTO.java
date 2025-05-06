package com.otcp.accounting.product.dto.response;

import com.otcp.accounting.product.entity.Product;
import com.otcp.accounting.product.entity.Warehouse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockResponseDTO {
    private int quantity;
    private Product product;
    private Warehouse warehouse;
}
