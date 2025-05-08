package com.otcp.accounting.product.dto.response;

import com.otcp.accounting.product.entity.Stock;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WarehouseResponseDTO {
    private String name;
    private String location;
    private List<Stock> stocks;
}
