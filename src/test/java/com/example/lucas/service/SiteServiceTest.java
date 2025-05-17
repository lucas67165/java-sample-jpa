package com.example.lucas.service;

import com.example.lucas.entity.Site;
import com.example.lucas.repository.SiteRepository;
import com.example.lucas.service.impl.SiteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SiteServiceTest {

    @Mock
    private SiteRepository siteRepository;

    private SiteService siteService;

    @BeforeEach
    void setUp() {
        siteService = new SiteServiceImpl(siteRepository);
    }

    @Test
    void findAll_shouldReturnAllSites() {
        // Given
        Site site1 = Site.builder()
                .id(1L)
                .title("Test Site 1")
                .siteUrl("https://test1.com")
                .build();
        
        Site site2 = Site.builder()
                .id(2L)
                .title("Test Site 2")
                .siteUrl("https://test2.com")
                .build();
        
        List<Site> expectedSites = Arrays.asList(site1, site2);
        when(siteRepository.findAll()).thenReturn(expectedSites);
        
        // When
        List<Site> actualSites = siteService.findAll();
        
        // Then
        assertEquals(expectedSites.size(), actualSites.size());
        assertEquals(expectedSites, actualSites);
        verify(siteRepository, times(1)).findAll();
    }
    
    @Test
    void findById_shouldReturnSiteWhenExists() {
        // Given
        Long siteId = 1L;
        Site expectedSite = Site.builder()
                .id(siteId)
                .title("Test Site")
                .siteUrl("https://test.com")
                .build();
        
        when(siteRepository.findById(siteId)).thenReturn(Optional.of(expectedSite));
        
        // When
        Optional<Site> actualSite = siteService.findById(siteId);
        
        // Then
        assertTrue(actualSite.isPresent());
        assertEquals(expectedSite, actualSite.get());
        verify(siteRepository, times(1)).findById(siteId);
    }
}