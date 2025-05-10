package com.otcp.accounting.common.utils;

import com.otcp.accounting.common.dto.FilterDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class PaginationUtils {

    public static Pageable buildPagination(FilterDTO filterDTO) {
        int currentPage = Optional.of(filterDTO.getCurrentPage())
                .filter(page -> page >= 0)
                .orElse(0);

        int pageSize = Optional.of(filterDTO.getPageSize())
                .filter(size -> size > 0)
                .orElse(10);

        String sortColumn = Optional.ofNullable(filterDTO.getSortingColumn())
                .filter(s -> !s.isBlank())
                .orElse("id");

        Sort.Direction direction = filterDTO.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC;

        return PageRequest.of(currentPage, pageSize, Sort.by(direction, sortColumn));
    }
}
