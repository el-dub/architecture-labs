package com.kpi.warehouse.dto.orderdetail;

import com.kpi.warehouse.dto.product.ProductDto;
import lombok.Data;

@Data
public class OrderDetailDto {

    private Long orderDetailId;
    private ProductDto product;
    private Integer quantity;
}
