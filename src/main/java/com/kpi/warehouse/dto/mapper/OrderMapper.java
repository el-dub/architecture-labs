package com.kpi.warehouse.dto.mapper;

import com.kpi.warehouse.dto.order.OrderDto;
import com.kpi.warehouse.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderDetailMapper.class})
public interface OrderMapper {

    @Mapping(source = "client.userId", target = "clientId")
    OrderDto toDto(Order order);
}
