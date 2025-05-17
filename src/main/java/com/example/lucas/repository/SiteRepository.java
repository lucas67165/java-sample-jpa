package com.example.lucas.repository;

import com.example.lucas.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Long> {
    boolean existsBySiteUrl(String siteUrl);

    Optional<Site> findBySiteUrl(String siteUrl);
}