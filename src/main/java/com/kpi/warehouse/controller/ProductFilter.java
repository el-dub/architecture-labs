package com.kpi.warehouse.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductFilter {
    private String name;
    private Integer quantityFrom;
    private Integer quantityTo;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;

    public static ProductFilterBuilder builder() {
        return new ProductFilterBuilder();
    }

    public static class ProductFilterBuilder {
        private String name;
        private Integer quantityFrom;
        private Integer quantityTo;
        private BigDecimal priceFrom;
        private BigDecimal priceTo;

        ProductFilterBuilder() {
        }

        public ProductFilterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductFilterBuilder quantityFrom(Integer quantityFrom) {
            this.quantityFrom = quantityFrom;
            return this;
        }

        public ProductFilterBuilder quantityTo(Integer quantityTo) {
            this.quantityTo = quantityTo;
            return this;
        }

        public ProductFilterBuilder priceFrom(BigDecimal priceFrom) {
            this.priceFrom = priceFrom;
            return this;
        }

        public ProductFilterBuilder priceTo(BigDecimal priceTo) {
            this.priceTo = priceTo;
            return this;
        }

        public ProductFilter build() {
            return new ProductFilter(name, quantityFrom, quantityTo, priceFrom, priceTo);
        }

        public String toString() {
            return "ProductFilter.ProductFilterBuilder(name=" + this.name + ", quantityFrom=" + this.quantityFrom + ", quantityTo=" + this.quantityTo + ", priceFrom=" + this.priceFrom + ", priceTo=" + this.priceTo + ")";
        }
    }
}
