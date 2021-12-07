package com.kpi.warehouse.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public class AndSpecification<T> extends CompositeSpecification<T> {

    private final ISpecification<T> one;
    private final ISpecification<T> other;

    public AndSpecification(ISpecification<T> x, ISpecification<T> y) {
        one = x;
        other = y;
    }

    @Override
    public Specification<T> getSpec() {
        return Specification.where(one.getSpec().and(other.getSpec()));
    }
}
