package com.otcp.accounting.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryDTO {

    @NotNull(message = "Category ID is mandatory")
    private Long id;

    @NotBlank(message = "Category name is mandatory")
    private String name;

    @Size(max = 255, message = "Description can be up to 255 characters")
    private String description;
}
