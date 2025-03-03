package com.otcp.accounting.invoice.entity;

import com.otcp.accounting.common.base.BaseEntity;
import com.otcp.accounting.product.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InvoiceItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    @Positive(message = "Unit price must be positive")
    private BigDecimal unitPrice;

    @PositiveOrZero(message = "Total must be zero or positive")
    private BigDecimal total;

}
