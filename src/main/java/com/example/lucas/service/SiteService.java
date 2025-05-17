package com.example.lucas.service;

import com.example.lucas.entity.Site;

import java.util.List;
import java.util.Optional;

public interface SiteService {
    Site create(Site site);
    List<Site> findAll();
    Optional<Site> findById(Long id);
    Site update(Site site);
    void deleteById(Long id);
    boolean existsBySiteUrl(String siteUrl);
}