package com.example.lucas.controller;

import com.example.lucas.data.dto.SiteDTO;
import com.example.lucas.data.request.SiteCreateRequest;
import com.example.lucas.data.response.Result;
import com.example.lucas.entity.Site;
import com.example.lucas.entity.Site_;
import com.example.lucas.exception.ValidationException;
import com.example.lucas.mapper.SiteMapper;
import com.example.lucas.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;

    /**
     * Create
     */
    @PostMapping
    public ResponseEntity<Result<SiteDTO>> create(@Valid @RequestBody SiteCreateRequest req) {
        // guard duplicate URL
        Map<String, String> validationErrors = new HashMap<>();
        if (siteService.existsBySiteUrl(req.getSiteUrl())) {
            validationErrors.put(Site_.SITE_URL, "Site URL already exists!");
        }

        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }

        Site site = SiteMapper.INSTANCE.toSite(req);
        site = siteService.create(site);
        SiteDTO result = SiteMapper.INSTANCE.toSiteDTO(site);
        return ResponseEntity.status(HttpStatus.CREATED).body(Result.ok(result));
    }

    /**
     * Get all Sites
     */
    @GetMapping
    public ResponseEntity<Result<List<SiteDTO>>> getAll() {
        List<Site> sites = siteService.findAll();
        List<SiteDTO> siteDTOList = sites.stream()
                .map(SiteMapper.INSTANCE::toSiteDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(Result.ok(siteDTOList));
    }

    /**
     * Get a Site by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result<SiteDTO>> getById(@PathVariable Long id) {
        Site site = siteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found!"));

        return ResponseEntity.ok(Result.ok(SiteMapper.INSTANCE.toSiteDTO(site)));
    }

    /**
     * Update an existing Site
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result<SiteDTO>> update(@PathVariable Long id, @Valid @RequestBody SiteDTO siteDTO) {
        Site existingSite = siteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found!"));

        existingSite.setTitle(siteDTO.getTitle());
        existingSite.setLogoUrl(siteDTO.getLogoUrl());
        existingSite.setSiteUrl(siteDTO.getSiteUrl());
        existingSite.setThemeColor(siteDTO.getThemeColor());

        Site updatedSite = siteService.update(existingSite);
        return ResponseEntity.ok(Result.ok(SiteMapper.INSTANCE.toSiteDTO(updatedSite)));
    }

    /**
     * Delete a Site by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        siteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}