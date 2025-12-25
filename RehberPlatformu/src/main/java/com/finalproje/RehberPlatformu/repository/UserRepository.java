package com.finalproje.RehberPlatformu.repository;

import com.finalproje.RehberPlatformu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}