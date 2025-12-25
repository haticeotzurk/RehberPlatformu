package com.finalproje.RehberPlatformu.repository;

import com.finalproje.RehberPlatformu.entity.HistoricalPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoricalPlaceRepository extends JpaRepository<HistoricalPlace, Long> {
    // Bölge ID'sine göre yerleri çekmek için
    List<HistoricalPlace> findByRegion_Id(Long regionId);
}