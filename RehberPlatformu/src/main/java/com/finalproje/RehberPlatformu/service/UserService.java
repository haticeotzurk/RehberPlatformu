package com.finalproje.RehberPlatformu.service;

import com.finalproje.RehberPlatformu.entity.User;
import com.finalproje.RehberPlatformu.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; // 1. Eklenecek
import org.springframework.security.core.userdetails.UsernameNotFoundException; // 2. Eklenecek
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.stereotype.Service;

import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
@Service
// !!! BURAYI EKLEYİN !!!
public class UserService implements UserDetailsService { 

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; 
    
    // Constructor aynı kalır
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kullanıcıyı veritabanından çek
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + username);
        }

        // --- KRİTİK DEĞİŞİKLİK BURADA BAŞLAR ---
        
        // 1. Kullanıcının rolünü (örn: "USER" veya "ADMIN") al.
        String userRole = user.getRole(); 

        // 2. Rolü, Spring Security'nin beklediği GrantedAuthority nesnesine dönüştür.
        List<GrantedAuthority> authorities = Collections.singletonList(
            new SimpleGrantedAuthority(userRole)
        );
        
        // 3. UserDetails nesnesini oluştururken bu yetki listesini kullan.
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            authorities // ARTIK BOŞ DEĞİL!
        );
    }

    public User registerUser(User newUser) {
        // ... (Bu metot aynı kalır, kayıt işlemini yönetir)
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanılıyor!");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        
        return userRepository.save(newUser);
    }
    
    // findByUsername ve checkPassword metotlarını koruyabiliriz, 
    // ancak giriş yetkilendirmesi artık tamamen loadUserByUsername tarafından yönetilir.
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}