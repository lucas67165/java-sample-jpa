package com.example.lucas.service.impl;

import com.example.lucas.data.filter.CategoryFilter;
import com.example.lucas.entity.Category;
import com.example.lucas.entity.Category_;
import com.example.lucas.exception.ValidationException;
import com.example.lucas.repository.CategoryRepository;
import com.example.lucas.service.CategoryService;
import com.example.lucas.spec.CategorySpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Lucas
 * date:   2025-04-17 14:32
 * @description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        Map<String, String> validationErrors = new HashMap<>();

        if (category.getSiteId() == null) {
            validationErrors.put(Category_.SITE_ID, "Site ID is missing.");
        }

        if (!category.getCategoryName().trim().isEmpty()) {
            validationErrors.put(Category_.CATEGORY_NAME, "Category name is missing.");
        }

        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
        return categoryRepository.save(category);
    }

    public Page<Category> getCategories(CategoryFilter filter) {
        Sort sort = Sort.by(
                Sort.Direction.fromString(filter.getDirection()),
                filter.getSortBy()
        );
        Pageable pageable = PageRequest.of(filter.getPageNumber() - 1, filter.getPageSize(), sort);
        CategorySpec spec = new CategorySpec(filter);
        return categoryRepository.findAll(spec, pageable);
    }
}
