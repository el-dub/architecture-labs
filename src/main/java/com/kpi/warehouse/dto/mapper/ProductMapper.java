package com.kpi.warehouse.dto.mapper;

import com.kpi.warehouse.dto.product.ProductCreateDto;
import com.kpi.warehouse.dto.product.ProductDto;
import com.kpi.warehouse.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromDto(ProductCreateDto productDto);

    Product fromDto(ProductDto productDto);

    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> products);

    List<Product> fromDtoList(List<ProductCreateDto> productDtoList);
}
