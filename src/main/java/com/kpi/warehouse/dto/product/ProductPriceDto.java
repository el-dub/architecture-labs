package com.kpi.warehouse.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductPriceDto {

    private Long productId;
    private String name;
    private BigDecimal sellingPrice;
}
