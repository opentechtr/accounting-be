package com.otcp.accounting.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilterDTO {
    private int currentPage;
    private int pageSize;
    private String sortingColumn;
    private boolean asc;
}
