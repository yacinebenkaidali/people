package com.checkconsulting.person.features.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * PersonSpecification
 */
public class PersonSpecification {
    public static Specification<Person> multiCriteriaSearch(Optional<String> lastName, Optional<String> firstName,
            Optional<Integer> minAge, Optional<Integer> maxAge) {
        return new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (lastName.isPresent()) {
                    predicates
                            .add(criteriaBuilder.like(root.get("lastName"), "%" + lastName.get().toLowerCase() + "%"));
                }
                if (firstName.isPresent()) {
                    predicates.add(
                            criteriaBuilder.like(root.get("firstName"), "%" + firstName.get().toLowerCase() + "%"));
                }
                if (minAge.isPresent()) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), minAge.get()));
                }
                if (maxAge.isPresent()) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), maxAge.get()));
                }
                if (!predicates.isEmpty()) {
                    return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
                }
                return criteriaBuilder.conjunction();
            }
        };
    }

}