package com.otcp.accounting.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.otcp.accounting.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
@NoArgsConstructor
public class Warehouse extends BaseEntity {
    @NotBlank(message = "{BAD_REQUEST_WAREHOUSE_EMPTY_NAME_EXCEPTION}")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "{BAD_REQUEST_WAREHOUSE_EMPTY_LOCATION_EXCEPTION}")
    private String location;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stocks = new ArrayList<>();

}
