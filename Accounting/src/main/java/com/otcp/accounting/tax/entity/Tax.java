package com.otcp.accounting.tax.entity;

import com.otcp.accounting.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "tax")
public class Tax extends BaseEntity {
    @NotBlank(message = "Tax name is mandatory")
    @Column(unique = true)
    private String name;

    @Positive(message = "Rate must be positive")
    private BigDecimal rate;

    @Size(max = 500, message = "Description can be up to 500 characters")
    private String description;



    // Detailed Getters and setters including isActive toggle
}
