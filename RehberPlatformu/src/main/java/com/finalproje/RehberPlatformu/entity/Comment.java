package com.finalproje.RehberPlatformu.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String content; 
    
    private String imageUrl; // Opsiyonel resim URL'si
    
    private LocalDateTime commentDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private HistoricalPlace place; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; 

    // --- MANUEL GETTER, SETTER ve CONSTRUCTOR METOTLARI ---

    // Boş Kurucu Metot (NoArgsConstructor yerine)
    public Comment() {
    }

    // Id Getter ve Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Content Getter ve Setter
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // ImageUrl Getter ve Setter
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // CommentDate Getter ve Setter
    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    // Place Getter ve Setter
    public HistoricalPlace getPlace() {
        return place;
    }

    public void setPlace(HistoricalPlace place) {
        this.place = place;
    }

    // User Getter ve Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}