package com.kpi.warehouse.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public class NotSpecification<T> extends CompositeSpecification<T> {

    private final ISpecification<T> wrapped;

    public NotSpecification(ISpecification<T> x) {
        wrapped = x;
    }

    @Override
    public Specification<T> getSpec() {
        return Specification.not(wrapped.getSpec());
    }
}
