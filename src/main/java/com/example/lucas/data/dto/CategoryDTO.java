package com.example.lucas.data.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Lucas
 * date:   2025-02-28 10:21
 * @description:
 */
@Data
public class CategoryDTO {
    private Long id;
    private Long siteId;
    private String categoryName;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
