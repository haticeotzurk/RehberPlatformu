package com.finalproje.RehberPlatformu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // Eklendi
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Eklendi

@SpringBootApplication
public class RehberPlatformuApplication {

	public static void main(String[] args) {
		SpringApplication.run(RehberPlatformuApplication.class, args);
	}

	// BCryptPasswordEncoder'ı bir Spring Bean'i olarak tanımlıyoruz
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}