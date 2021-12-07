package com.kpi.warehouse.dto.order;

import com.kpi.warehouse.dto.orderdetail.OrderDetailCreateDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {

    private Long orderId;
    private LocalDateTime orderTime;
    private Long clientId;
    private Boolean processed = false;
    private Boolean paid = false;
    private Boolean delivered = false;
    private List<OrderDetailCreateDto> orderDetails = new ArrayList<>();
}
