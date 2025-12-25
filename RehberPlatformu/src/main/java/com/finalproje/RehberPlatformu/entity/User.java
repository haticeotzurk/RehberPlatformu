package com.finalproje.RehberPlatformu.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Şifreli olarak saklanacak

    @Column(nullable = false)
    private String role = "USER"; // Varsayılan olarak "USER"

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments; 

    // --- MANUEL GETTER, SETTER ve CONSTRUCTOR METOTLARI ---

    // Boş Kurucu Metot (NoArgsConstructor yerine)
    public User() {
    }
    
    // Opsiyonel: Tüm temel alanları alan Kurucu Metot
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    // Id Getter ve Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Username Getter ve Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Password Getter ve Setter
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Role Getter ve Setter
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Comments Getter ve Setter
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}