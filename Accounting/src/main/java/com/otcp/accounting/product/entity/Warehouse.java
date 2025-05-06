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
    @NotBlank(message = "Warehouse name is mandatory")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Location is mandatory")
    private String location;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Stock> stocks = new ArrayList<>();

}
