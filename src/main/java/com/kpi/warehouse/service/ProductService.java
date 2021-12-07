package com.kpi.warehouse.service;

import com.kpi.warehouse.controller.ProductFilter;
import com.kpi.warehouse.dto.product.ProductCreateDto;
import com.kpi.warehouse.dto.product.ProductDto;
import com.kpi.warehouse.dto.product.ProductPriceDto;
import com.kpi.warehouse.model.Product;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductCreateDto productDto);

    List<ProductDto> addProducts(List<ProductCreateDto> productDtoList);

    List<ProductDto> getProducts(ProductFilter filter);

    List<ProductDto> getProductsFromAllServices(ProductFilter filter);

    ProductDto getProductByIdFromService(Long id);

    List<ProductPriceDto> getPriceList();

    void deleteProduct(Long id);

    List<Product> getProductsByIds(List<Long> ids);

    ProductDto updateProduct(ProductDto productDto);
}
