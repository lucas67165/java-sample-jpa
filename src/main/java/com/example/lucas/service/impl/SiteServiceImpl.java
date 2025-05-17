package com.example.lucas.service.impl;

import com.example.lucas.entity.Site;
import com.example.lucas.repository.SiteRepository;
import com.example.lucas.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteServiceImpl implements SiteService {

    private final SiteRepository siteRepository;

    @Override
    public Site create(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    @Override
    public Optional<Site> findById(Long id) {
        return siteRepository.findById(id);
    }

    @Override
    public Site update(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public void deleteById(Long id) {
        siteRepository.deleteById(id);
    }

    @Override
    public boolean existsBySiteUrl(String siteUrl) {
        return siteRepository.existsBySiteUrl(siteUrl);
    }
}