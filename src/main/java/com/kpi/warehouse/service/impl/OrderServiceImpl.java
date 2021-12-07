package com.kpi.warehouse.service.impl;

import com.kpi.warehouse.dto.mapper.OrderMapper;
import com.kpi.warehouse.dto.order.OrderCreateDto;
import com.kpi.warehouse.dto.order.OrderDto;
import com.kpi.warehouse.dto.orderdetail.OrderDetailCreateDto;
import com.kpi.warehouse.model.Order;
import com.kpi.warehouse.model.OrderDetail;
import com.kpi.warehouse.model.Product;
import com.kpi.warehouse.model.User;
import com.kpi.warehouse.repository.OrderRepository;
import com.kpi.warehouse.service.OrderService;
import com.kpi.warehouse.service.ProductService;
import com.kpi.warehouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final ProductService productService;

    @Override
    public OrderDto createOrder(OrderCreateDto orderCreateDto) {
        Long userId = orderCreateDto.getClientId();
        User user = userService.getUserById(userId);
        List<OrderDetail> orderDetails = populateOrderDetails(orderCreateDto);
        Order order = Order.builder()
                .client(user)
                .orderTime(LocalDateTime.now())
                .orderDetails(orderDetails)
                .delivered(false)
                .paid(false)
                .processed(false)
                .build();
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    @Override
    public void processOrder(Long orderId) {
        orderRepository.processOrder(orderId);
    }

    private List<OrderDetail> populateOrderDetails(OrderCreateDto orderCreateDto) {
        Map<Long, Integer> productQuantity = new HashMap<>();
        orderCreateDto.getOrderDetails()
                .forEach(orderDetailCreateDto -> productQuantity.put(orderDetailCreateDto.getProductId(),
                        orderDetailCreateDto.getQuantity()));
        List<Product> products = productService.getProductsByIds(orderCreateDto.getOrderDetails()
                .stream()
                .map(OrderDetailCreateDto::getProductId)
                .collect(Collectors.toList()));

        return products.stream()
                .map(product -> {
                    OrderDetail od = new OrderDetail();
                    od.setProduct(product);
                    od.setQuantity(productQuantity.get(product.getProductId()));
                    return od;
                })
                .collect(Collectors.toList());
    }
}
