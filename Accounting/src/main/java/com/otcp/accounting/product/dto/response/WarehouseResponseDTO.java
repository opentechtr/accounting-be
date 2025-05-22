package com.otcp.accounting.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponseDTO {
    private String name;
    private String location;
}
