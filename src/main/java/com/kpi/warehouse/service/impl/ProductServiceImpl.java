package com.kpi.warehouse.service.impl;

import com.kpi.warehouse.controller.ProductFilter;
import com.kpi.warehouse.dto.mapper.ProductMapper;
import com.kpi.warehouse.dto.product.ProductCreateDto;
import com.kpi.warehouse.dto.product.ProductDto;
import com.kpi.warehouse.dto.product.ProductPriceDto;
import com.kpi.warehouse.model.Product;
import com.kpi.warehouse.repository.ProductRepository;
import com.kpi.warehouse.repository.specification.CriteriaGreaterSpecification;
import com.kpi.warehouse.repository.specification.CriteriaLessSpecification;
import com.kpi.warehouse.repository.specification.CriteriaLikeSpecification;
import com.kpi.warehouse.repository.specification.ISpecification;
import com.kpi.warehouse.repository.specification.SearchCriteria;
import com.kpi.warehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final RestTemplate rest;

    @Value("${FILTER_SERVICE_URL}")
    private String FILTER_SERVICE_URL;

    @Value("${PRODUCT_SERVICE_URL}")
    private String PRODUCT_SERVICE_URL;

    @Override
    public ProductDto addProduct(ProductCreateDto productDto) {
        Product product = productMapper.fromDto(productDto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> addProducts(List<ProductCreateDto> productDtoList) {
        List<Product> products = productMapper.fromDtoList(productDtoList);
        return productMapper.toDtoList(productRepository.saveAll(products));
    }

    @Override
    public List<ProductDto> getProducts(ProductFilter filter) {

        ISpecification<Product> specification = buildSpecification(filter);
        if (specification == null) {
            List<Product> products = productRepository.findAll();
            return productMapper.toDtoList(products);
        } else {
            Specification<Product> spec = specification.getSpec();
            return productMapper.toDtoList(productRepository.findAll(spec));
        }

    }

    @Override
    public List<ProductDto> getProductsFromAllServices(ProductFilter filter) {
        List<ProductDto> products = getProducts(filter);
        products.addAll(getProductsFromFilterService(filter));
        return products;
    }

    private ISpecification<Product> buildSpecification(ProductFilter filter) {
        List<ISpecification<Product>> specifications = new ArrayList<>();
        if (filter.getName() != null) {
            specifications.add(new CriteriaLikeSpecification(new SearchCriteria("name", filter.getName())));
        }
        if (filter.getPriceFrom() != null) {
            specifications.add(new CriteriaGreaterSpecification(new SearchCriteria("sellingPrice", filter.getPriceFrom())));
        }
        if (filter.getPriceTo() != null) {
            specifications.add(new CriteriaLessSpecification(new SearchCriteria("sellingPrice", filter.getPriceTo())));
        }
        if (filter.getQuantityTo() != null) {
            specifications.add(new CriteriaLessSpecification(new SearchCriteria("quantity", filter.getQuantityTo())));
        }
        if (filter.getQuantityFrom() != null) {
            specifications.add(new CriteriaGreaterSpecification(new SearchCriteria("quantity", filter.getQuantityFrom())));
        }

        ISpecification<Product> productSpecification = null;

        for (ISpecification<Product> spec : specifications) {
            if (productSpecification == null) {
                productSpecification = spec;
            } else {
                productSpecification = productSpecification.and(spec);
            }
        }
        return productSpecification;
    }

    private List<ProductDto> getProductsFromFilterService(ProductFilter productFilter) {

        ProductDto[] products = rest.getForEntity(populateFilterServiceUrl(productFilter), ProductDto[].class).getBody();
        return products != null ? Arrays.asList(products) : new ArrayList<>();
    }

    public ProductDto getProductByIdFromService(Long id) {
        String url = PRODUCT_SERVICE_URL + "/details/" + id;
        return rest.getForEntity(url, ProductDto.class).getBody();
    }

    public List<ProductPriceDto> getPriceList() {
        String url = PRODUCT_SERVICE_URL + "/price-list";
        ProductPriceDto[] products = rest.getForEntity(url, ProductPriceDto[].class).getBody();
        return products != null ? Arrays.asList(products) : new ArrayList<>();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByIds(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productMapper.fromDto(productDto);
        return productMapper.toDto(productRepository.save(product));
    }

    private String populateFilterServiceUrl(ProductFilter filter) {
        StringBuilder params = new StringBuilder();
        if (filter.getName() != null) {
            if (params.length() == 0) {
                params.append("?");
            } else {
                params.append("&");
            }
            params.append("name").append("=").append(filter.getName());
        }

        if (filter.getPriceFrom() != null) {
            if (params.length() == 0) {
                params.append("?");
            } else {
                params.append("&");
            }
            params.append("priceFrom").append("=").append(filter.getPriceFrom());
        }

        if (filter.getPriceTo() != null) {
            if (params.length() == 0) {
                params.append("?");
            } else {
                params.append("&");
            }
            params.append("priceTo").append("=").append(filter.getPriceTo());
        }

        if (filter.getQuantityFrom() != null) {
            if (params.length() == 0) {
                params.append("?");
            } else {
                params.append("&");
            }
            params.append("quantityFrom").append("=").append(filter.getQuantityFrom());
        }

        if (filter.getQuantityTo() != null) {
            if (params.length() == 0) {
                params.append("?");
            } else {
                params.append("&");
            }
            params.append("quantityTo").append("=").append(filter.getQuantityTo());
        }
        String url = FILTER_SERVICE_URL + "/search";
        if (params.length() != 0) {
            url = url + params;
        }
        return url;
    }
}
