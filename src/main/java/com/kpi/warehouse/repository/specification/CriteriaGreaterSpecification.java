package com.kpi.warehouse.repository.specification;

import com.kpi.warehouse.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class CriteriaGreaterSpecification extends CompositeSpecification<Product> {

    private final SearchCriteria criteria;

    public CriteriaGreaterSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Specification<Product> getSpec() {
        return (Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                root.get(criteria.getKey()), criteria.getValue().toString());
    }
}
