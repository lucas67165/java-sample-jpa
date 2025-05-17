package com.example.lucas.data.dto;

import lombok.Data;

@Data
public class SiteDTO {
    private Long id;
    private String title;
    private String logoUrl;
    private String siteUrl;
    private String themeColor;
}