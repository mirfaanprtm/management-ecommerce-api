package com.example.livecodeecommerce.utils.specifications;

import org.springframework.data.jpa.domain.Specification;

public class Spec<T> {
    public Specification<T> findBy(SearchCriteria searchCriteria) {

        switch (searchCriteria.getOperator()) {
            case LIKE:
                return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%"));
            case EQUAL:
                return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue())));
            case NOT_EQUAL:
                return ((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
            default:
                throw new RuntimeException("Operator not supported");
        }

    }
}
