package com.otcp.accounting.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryDTO {
    @NotBlank(message = "Category name is mandatory")
    private String name;

    @Size(max = 255, message = "Description can be up to 255 characters")
    private String description;
}
