package com.finalproje.RehberPlatformu;

import com.finalproje.RehberPlatformu.entity.HistoricalPlace;
import com.finalproje.RehberPlatformu.entity.Region;
import com.finalproje.RehberPlatformu.entity.User;
import com.finalproje.RehberPlatformu.repository.HistoricalPlaceRepository;
import com.finalproje.RehberPlatformu.repository.RegionRepository;
import com.finalproje.RehberPlatformu.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final HistoricalPlaceRepository placeRepository; 
    private final RegionRepository regionRepository; 
    private final BCryptPasswordEncoder passwordEncoder; 

    public DataSeeder(UserRepository userRepository, HistoricalPlaceRepository placeRepository, RegionRepository regionRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.regionRepository = regionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            
            // 1. Kullanıcıları Ekleme
            User admin = new User();
            admin.setUsername("patron"); // Hata alınan satır 18'deki karakter düzeltildi
            admin.setPassword(passwordEncoder.encode("admin123")); // Şifre şifreleniyor
            admin.setRole("ADMIN"); // Hata alınan satır 18'deki karakter düzeltildi
            userRepository.save(admin);

            User user1 = new User();
            user1.setUsername("gezgin_ali");
            user1.setPassword(passwordEncoder.encode("123"));
            user1.setRole("USER"); // Hata alınan satır 18'deki karakter düzeltildi
            userRepository.save(user1);

            // 2. Bölgeleri Ekleme ve Map'e Atama
            List<String> regionNames = Arrays.asList(
                "Marmara Bölgesi", "Ege Bölgesi", "Akdeniz Bölgesi", 
                "İç Anadolu Bölgesi", "Karadeniz Bölgesi", 
                "Doğu Anadolu Bölgesi", "Güneydoğu Anadolu Bölgesi"
            );
            
            // Map hatasını gidermek için tür açıkça belirtildi (Function<String, Region>)
            List<Region> regions = regionNames.stream()
                .map(Region::new)
                .collect(Collectors.toList());
            
            regionRepository.saveAll(regions);
            
            // Hızlı erişim için Map oluşturma
            Map<String, Region> regionMap = regions.stream()
                .collect(Collectors.toMap(Region::getName, region -> region));

            // 3. Tarihi Yerleri Ekleme
            // HistoricalPlace constructor'ı: (String name, String description, String locationCity, Region region)
            
            HistoricalPlace place1 = new HistoricalPlace("Ayasofya Camii", "Dünya mimarlık tarihinin başyapıtlarından biri.", "İstanbul", regionMap.get("Marmara Bölgesi"));
            place1.setTransportation("Tramvay, otobüs ve feribot ile ulaşım mümkündür.");
            
            HistoricalPlace place2 = new HistoricalPlace("Efes Antik Kenti", "İzmir'in Selçuk ilçesinde bulunan, antik dünyanın en önemli yerleşimlerinden.", "İzmir", regionMap.get("Ege Bölgesi"));
            place2.setTransportation("İzmir Adnan Menderes Havalimanı'ndan Selçuk'a tren veya otobüs.");
            
            HistoricalPlace place3 = new HistoricalPlace("Kapadokya", "Peribacaları ve eşsiz doğal oluşumlarıyla ünlü.", "Nevşehir", regionMap.get("İç Anadolu Bölgesi"));
            
            placeRepository.saveAll(Arrays.asList(place1, place2, place3));

            System.out.println("--- GEREKLİ VERİLER BAŞARIYLA YÜKLENDİ (7 Bölge, 2 Kullanıcı, 3 Yer) ---");
        }
    }
}