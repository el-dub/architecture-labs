package com.kpi.warehouse.controller;

import com.kpi.warehouse.dto.order.OrderCreateDto;
import com.kpi.warehouse.dto.order.OrderDto;
import com.kpi.warehouse.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        return orderService.createOrder(orderCreateDto);
    }

    @PutMapping("/process/{id}")
    public void processOrder(@PathVariable Long id) {
        orderService.processOrder(id);
    }
}
