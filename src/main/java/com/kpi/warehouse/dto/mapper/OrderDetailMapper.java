package com.kpi.warehouse.dto.mapper;

import com.kpi.warehouse.dto.orderdetail.OrderDetailCreateDto;
import com.kpi.warehouse.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(source = "product.productId", target = "productId")
    OrderDetailCreateDto toDto(OrderDetail createDto);
}
