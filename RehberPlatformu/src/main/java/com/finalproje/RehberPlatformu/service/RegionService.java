package com.finalproje.RehberPlatformu.service;


import com.finalproje.RehberPlatformu.entity.Region;
import com.finalproje.RehberPlatformu.repository.RegionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegionService {
    
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }
    
    public Region getRegionById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }
    
    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }
}