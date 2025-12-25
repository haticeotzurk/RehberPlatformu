package com.finalproje.RehberPlatformu.service;

import com.finalproje.RehberPlatformu.entity.HistoricalPlace;
import com.finalproje.RehberPlatformu.repository.HistoricalPlaceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricalPlaceService {

    private final HistoricalPlaceRepository placeRepository;

    public HistoricalPlaceService(HistoricalPlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<HistoricalPlace> getAllPlaces() {
        return placeRepository.findAll();
    }
    
    public Optional<HistoricalPlace> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }
    
    public List<HistoricalPlace> getPlacesByRegion(Long regionId) {
        return placeRepository.findByRegion_Id(regionId);
    }
    
    public HistoricalPlace savePlace(HistoricalPlace place) {
        return placeRepository.save(place);
    }
}