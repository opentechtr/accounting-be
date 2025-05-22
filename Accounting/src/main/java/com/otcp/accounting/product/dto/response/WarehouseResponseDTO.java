package com.otcp.accounting.product.dto.response;

import com.otcp.accounting.product.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponseDTO {
    private String name;
    private String location;
    private List<Stock> stocks;
}