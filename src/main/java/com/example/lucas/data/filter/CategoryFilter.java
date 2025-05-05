package com.example.lucas.data.filter;

import com.example.lucas.data.enums.SortDirection;
import com.example.lucas.entity.Category_;
import com.example.lucas.validation.ValidSortField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;

/**
 * @author : Lucas
 * date:   2025-04-17 14:38
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryFilter extends PaginationFilter {
    /**
     * site owner
     */
    private Long siteId;
    /**
     * category name
     */
    private String categoryName;
    /**
     * 1: active, 0: inactive
     */
    private Integer status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String endDate;

    @ValidSortField(allowed = {Category_.ID, Category_.CATEGORY_NAME})
    private String sortBy = Category_.ID;
    @Pattern(regexp = SortDirection.REGEX)
    private String direction = SortDirection.DESC.name();
}
