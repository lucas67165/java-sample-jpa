package com.example.lucas.controller;

import com.example.lucas.data.dto.CategoryDTO;
import com.example.lucas.data.request.CategoryCreateRequest;
import com.example.lucas.data.response.Result;
import com.example.lucas.entity.Category;
import com.example.lucas.mapper.CategoryMapper;
import com.example.lucas.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : Lucas
 * date:   2025-04-17 14:30
 * @description:
 */
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Create
     */
    @PostMapping
    @PreAuthorize("hasAuthority('category-user') or hasRole('developer') or hasRole('admin')")
    public ResponseEntity<Result<CategoryDTO>> create(@Valid @RequestBody CategoryCreateRequest request) {
        Category category = CategoryMapper.INSTANCE.toCategory(request);
        category = categoryService.create(category);

        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.toCategoryDTO(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(Result.ok(categoryDTO));
    }
}
