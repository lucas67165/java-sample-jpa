package com.example.lucas.data.request;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SiteCreateRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String logoUrl;

    @URL(message = "Invalid URL format.")
    @NotBlank(message = "Site URL is required.")
    private String siteUrl;

    @Pattern(
        regexp = "^#([A-Fa-f0-9]{6})$",
        message = "Theme color must be in hex format, e.g., #FFFFFF"
    )
    private String themeColor;
}