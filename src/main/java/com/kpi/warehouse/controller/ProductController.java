package com.kpi.warehouse.controller;

import com.kpi.warehouse.dto.product.ProductCreateDto;
import com.kpi.warehouse.dto.product.ProductDto;
import com.kpi.warehouse.dto.product.ProductPriceDto;
import com.kpi.warehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductCreateDto productCreateDto) {
        return productService.addProduct(productCreateDto);
    }

    @PostMapping("/supply")
    public List<ProductDto> addProducts(@RequestBody List<ProductCreateDto> productCreateDtoList) {
        return productService.addProducts(productCreateDtoList);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping
    public List<ProductDto> getProducts(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) Integer quantityFrom,
                                        @RequestParam(required = false) Integer quantityTo,
                                        @RequestParam(required = false) BigDecimal priceFrom,
                                        @RequestParam(required = false) BigDecimal priceTo) {
        ProductFilter filter = ProductFilter.builder()
                .name(name)
                .quantityFrom(quantityFrom)
                .quantityTo(quantityTo)
                .priceFrom(priceFrom)
                .priceTo(priceTo)
                .build();
        return productService.getProducts(filter);
    }

    @GetMapping("/all")
    public List<ProductDto> getProductsFromAllServices(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) Integer quantityFrom,
                                                       @RequestParam(required = false) Integer quantityTo,
                                                       @RequestParam(required = false) BigDecimal priceFrom,
                                                       @RequestParam(required = false) BigDecimal priceTo) {
        ProductFilter filter = ProductFilter.builder()
                .name(name)
                .quantityFrom(quantityFrom)
                .quantityTo(quantityTo)
                .priceFrom(priceFrom)
                .priceTo(priceTo)
                .build();
        return productService.getProductsFromAllServices(filter);
    }

    @GetMapping("/prices")
    public List<ProductPriceDto> getProductPrices() {
        return productService.getPriceList();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductByIdFromService(id);
    }
}
