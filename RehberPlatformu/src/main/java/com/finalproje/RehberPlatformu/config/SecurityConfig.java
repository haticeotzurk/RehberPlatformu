package com.finalproje.RehberPlatformu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
            .authorizeHttpRequests(requests -> requests
                // H2 Konsolu ve Statik kaynaklara herkesin erişimini sağla
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                
                // Kayıt ve Giriş sayfalarına erişimi sağla
                .requestMatchers("/", "/region/**", "/place/**", "/login", "/register").permitAll()
                
                // Yorum ekleme, yer ekleme gibi işlemler için kullanıcı girişi iste
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") // Kullanılacak özel giriş sayfası
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            )
            // H2 Konsolu için gerekli ayarlar
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            ); 

        return http.build();
    }
}