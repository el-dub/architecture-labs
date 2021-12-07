package com.kpi.warehouse.service;

import com.kpi.warehouse.dto.order.OrderCreateDto;
import com.kpi.warehouse.dto.order.OrderDto;

import java.math.BigDecimal;

public interface OrderService {

    OrderDto createOrder(OrderCreateDto orderCreateDto);

    void processOrder(Long orderId);
}
