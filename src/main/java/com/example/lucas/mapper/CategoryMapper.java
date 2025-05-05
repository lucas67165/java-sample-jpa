package com.example.lucas.mapper;

import com.example.lucas.data.dto.CategoryDTO;
import com.example.lucas.data.request.CategoryCreateRequest;
import com.example.lucas.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : Lucas
 * date:   2025-04-26 17:25
 * @description:
 */
@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryCreateRequest request);

    CategoryDTO toCategoryDTO(Category category);
}
