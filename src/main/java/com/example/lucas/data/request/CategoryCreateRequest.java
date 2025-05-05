package com.example.lucas.data.request;

import lombok.Data;

/**
 * @author : Lucas
 * date:   2025-04-26 16:50
 * @description:
 */
@Data
public class CategoryCreateRequest {
    private Long siteId;
    private String categoryName;
    private Integer status;
}
