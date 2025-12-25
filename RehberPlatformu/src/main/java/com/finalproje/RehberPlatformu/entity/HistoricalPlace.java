package com.finalproje.RehberPlatformu.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "historical_places")
public class HistoricalPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Yer Adı (Örn: Efes Antik Kenti)
    private String description; // Genel Bilgi
    private String locationCity;
    private String transportation; // Ulaşım Yolu

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id") 
    private Region region; // Hangi bölgeye ait
    
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Comment> comments; 
    
    // --- MANUEL KURUCU METOTLAR ---

    // Boş Kurucu Metot (NoArgsConstructor yerine)
    public HistoricalPlace() {
    }
    
    // Parametreli Kurucu Metot (Mevcut olanı korundu)
    public HistoricalPlace(String name, String description, String locationCity, Region region) {
        this.name = name;
        this.description = description;
        this.locationCity = locationCity;
        this.region = region;
    }

    // --- MANUEL GETTER VE SETTER METOTLARI ---
    
    // Id Getter ve Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Name Getter ve Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Description Getter ve Setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // LocationCity Getter ve Setter
    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    // Transportation Getter ve Setter
    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    // Region Getter ve Setter
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    // Comments Getter ve Setter
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}