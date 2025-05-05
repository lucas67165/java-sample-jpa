package com.example.lucas.spec;

import com.example.lucas.data.filter.UserFilter;
import com.example.lucas.entity.User;
import com.example.lucas.entity.User_;
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
 * date:   2025-03-11 14:04
 * @description:
 */
public record UserSpec(UserFilter userFilter) implements Specification<User> {
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        // Filter by username (case-insensitive)
        if (userFilter.getUsername() != null && !userFilter.getUsername().isEmpty()) {
            predicates.add(cb.like(
                    cb.upper(root.get(User_.username)),
                    "%" + userFilter.getUsername().toUpperCase() + "%"
            ));
        }

        // Filter by email
        if (userFilter.getEmail() != null && !userFilter.getEmail().isEmpty()) {
            predicates.add(cb.equal(root.get(User_.email), userFilter.getEmail()));
        }

        if (userFilter.getGender() != null){
            predicates.add(cb.equal(root.get(User_.gender), userFilter.getGender()));
        }

        // Filter by creation date range if provided
        if (userFilter.getStartDate() != null && !userFilter.getStartDate().isEmpty()) {
            LocalDate startDate = LocalDate.parse(userFilter.getStartDate());
            predicates.add(cb.greaterThanOrEqualTo(
                    root.get(User_.createdAt),
                    startDate.atStartOfDay()
            ));
        }
        if (userFilter.getEndDate() != null && !userFilter.getEndDate().isEmpty()) {
            LocalDate endDate = LocalDate.parse(userFilter.getEndDate());
            predicates.add(cb.lessThanOrEqualTo(
                    root.get(User_.createdAt),
                    endDate.atTime(LocalTime.MAX)
            ));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
