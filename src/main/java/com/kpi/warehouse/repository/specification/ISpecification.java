package com.kpi.warehouse.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public interface ISpecification<T> {

    public abstract Specification<T> getSpec();

    public abstract ISpecification<T> and(ISpecification<T> specification);

    public abstract ISpecification<T> or(ISpecification<T> specification);

    public abstract ISpecification<T> not();
}
