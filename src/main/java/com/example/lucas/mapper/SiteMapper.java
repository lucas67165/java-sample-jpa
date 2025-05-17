package com.example.lucas.mapper;

import com.example.lucas.data.dto.SiteDTO;
import com.example.lucas.data.request.SiteCreateRequest;
import com.example.lucas.entity.Site;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SiteMapper {
    SiteMapper INSTANCE = Mappers.getMapper(SiteMapper.class);

    SiteDTO toSiteDTO(Site site);
    Site toSite(SiteDTO siteDTO);
    Site toSite(SiteCreateRequest siteCreateRequest);
}