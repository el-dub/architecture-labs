package com.kpi.warehouse.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public abstract class CompositeSpecification<T> implements ISpecification<T> {

    @Override
    public abstract Specification<T> getSpec();

    @Override
    public ISpecification<T> and(ISpecification<T> other) {
        return new AndSpecification<T>(this, other);
    }

    @Override
    public ISpecification<T> or(ISpecification<T> other) {
        return new OrSpecification<T>(this, other);
    }

    @Override
    public ISpecification<T> not() {
        return new NotSpecification<T>(this);
    }
}
