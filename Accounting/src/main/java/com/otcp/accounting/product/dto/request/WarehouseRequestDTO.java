package com.otcp.accounting.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseRequestDTO {
    private String name;
    private String location;
}
