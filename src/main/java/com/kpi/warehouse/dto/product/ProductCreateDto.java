package com.kpi.warehouse.dto.product;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductCreateDto {

    private String name;
    private Integer quantity;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private LocalDate supplyDate;
}
