package com.example.lucas.spec;

import com.example.lucas.data.filter.CategoryFilter;
import com.example.lucas.entity.Category;
import com.example.lucas.entity.Category_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Lucas
 * date:   2025-04-17 14:33
 * @description:
 */
public record CategorySpec(CategoryFilter categoryFilter) implements Specification<Category> {

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        // Filter by userId
        if (categoryFilter.getSiteId() != null) {
            predicates.add(cb.equal(root.get(Category_.SITE_ID), categoryFilter.getSiteId()));
        }

        // Filter by categoryName (case-insensitive)
        if (categoryFilter.getCategoryName() != null && !categoryFilter.getCategoryName().isEmpty()) {
            predicates.add(cb.like(
                    cb.upper(root.get(Category_.categoryName)),
                    "%" + categoryFilter.getCategoryName().toUpperCase() + "%"
            ));
        }

        // Filter by creation date range if provided
        if (categoryFilter.getStartDate() != null && !categoryFilter.getStartDate().isEmpty()) {
            LocalDate startDate = LocalDate.parse(categoryFilter.getStartDate());
            predicates.add(cb.greaterThanOrEqualTo(
                    root.get(Category_.createdAt),
                    startDate.atStartOfDay()
            ));
        }

        if (categoryFilter.getEndDate() != null && !categoryFilter.getEndDate().isEmpty()) {
            LocalDate endDate = LocalDate.parse(categoryFilter().getEndDate());
            predicates.add(cb.lessThanOrEqualTo(
                    root.get(Category_.createdAt),
                    endDate.atTime(LocalTime.MAX)
            ));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
