package com.kpi.warehouse.dto.order;

import com.kpi.warehouse.dto.orderdetail.OrderDetailCreateDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDto {

    private Long clientId;
    private List<OrderDetailCreateDto> orderDetails;
}
