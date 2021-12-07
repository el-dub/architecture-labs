package com.kpi.warehouse.dto.orderdetail;

import lombok.Data;

@Data
public class OrderDetailCreateDto {

    private Long productId;
    private Integer quantity;
}
