package com.otcp.accounting.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.otcp.accounting.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "category",
        indexes = {
                @Index(name = "idx_category_name", columnList = "name")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity {

    @NotBlank(message = "Category name is mandatory")
    @Column(unique = true)
    private String name;

    @Size(max = 255, message = "Description can be up to 255 characters")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();
}