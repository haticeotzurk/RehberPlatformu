package com.finalproje.RehberPlatformu.controller;

import com.finalproje.RehberPlatformu.entity.Comment;
import com.finalproje.RehberPlatformu.entity.HistoricalPlace;
import com.finalproje.RehberPlatformu.entity.Region;
import com.finalproje.RehberPlatformu.service.HistoricalPlaceService;
import com.finalproje.RehberPlatformu.service.RegionService;
import com.finalproje.RehberPlatformu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.security.Principal;

@Controller
public class WebController {

    private final HistoricalPlaceService placeService;
    private final RegionService regionService;
    private final UserService userService;

    public WebController(HistoricalPlaceService placeService, RegionService regionService, UserService userService) {
        this.placeService = placeService;
        this.regionService = regionService;
        this.userService = userService;
    }

    // Sol menüde her zaman 7 bölgeyi göstermek için ortak metot
    private void addRegionsToModel(Model model) {
        model.addAttribute("regions", regionService.getAllRegions());
    }
    
    // --- 1. Ana Sayfa (Tüm yerleri listeler) ---
    @GetMapping("/")
    public String home(Model model) {
        addRegionsToModel(model); // Sol menüyü ekle
        
        // Tüm yerleri tarihe göre (ID'ye göre) getirir
        model.addAttribute("places", placeService.getAllPlaces()); 
        model.addAttribute("pageTitle", "Keşfedilecek Tüm Gezi Yerleri");
        
        return "index"; // index.html
    }
    
    // --- 2. Bölgeye Göre Tarihi Yerleri Listeleme ---
    @GetMapping("/region/{regionId}")
    public String getPlacesByRegion(@PathVariable Long regionId, Model model) {
        addRegionsToModel(model); // Sol menüyü ekle
        
        Region currentRegion = regionService.getRegionById(regionId);
        if (currentRegion == null) {
            // Bölge bulunamazsa ana sayfaya yönlendir
            return "redirect:/"; 
        }
        
        // Bölgeye ait yerleri getir
        model.addAttribute("places", placeService.getPlacesByRegion(regionId));
        model.addAttribute("pageTitle", currentRegion.getName() + " Gezi Yerleri");
        
        return "index"; // index.html'i tekrar kullan
    }
    
    // --- 3. Tarihi Yer Detay Sayfası ---
    @GetMapping("/place/{placeId}")
    public String getPlaceDetail(@PathVariable Long placeId, Model model, Principal principal) {
        
        HistoricalPlace place = placeService.getPlaceById(placeId)
                .orElseThrow(() -> new RuntimeException("Tarihi Yer bulunamadı!"));
        
        addRegionsToModel(model); // Sol menüyü ekle
        model.addAttribute("place", place);
        
        // Yorum formu için boş bir Comment nesnesi ekle
        model.addAttribute("newComment", new Comment()); 
        
        // Kullanıcının giriş yapıp yapmadığını kontrol et
        if (principal != null) {
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        
        return "place_detail"; // place_detail.html
    }
}