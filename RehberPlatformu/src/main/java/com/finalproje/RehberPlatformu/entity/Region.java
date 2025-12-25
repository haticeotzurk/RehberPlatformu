package com.finalproje.RehberPlatformu.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // Örn: Akdeniz Bölgesi

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<HistoricalPlace> places;

    // --- MANUEL KURUCU METOTLAR ---

    // Boş Kurucu Metot (NoArgsConstructor yerine)
    public Region() {
    }
    
    // Parametreli Kurucu Metot (Mevcut olanı korundu)
    public Region(String name) {
        this.name = name;
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

    // Places Getter ve Setter
    public List<HistoricalPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<HistoricalPlace> places) {
        this.places = places;
    }
}