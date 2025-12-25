package com.finalproje.RehberPlatformu.controller;

import com.finalproje.RehberPlatformu.entity.Comment;
import com.finalproje.RehberPlatformu.entity.HistoricalPlace;
import com.finalproje.RehberPlatformu.entity.User;
import com.finalproje.RehberPlatformu.service.CommentService;
import com.finalproje.RehberPlatformu.service.HistoricalPlaceService;
import com.finalproje.RehberPlatformu.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final HistoricalPlaceService placeService;
    private final UserService userService;

    public CommentController(CommentService commentService, HistoricalPlaceService placeService, UserService userService) {
        this.commentService = commentService;
        this.placeService = placeService;
        this.userService = userService;
    }
    
    // --- Yorum Ekleme ---
    @PostMapping("/place/{placeId}/comment")
    public String addComment(@PathVariable Long placeId, 
                             @RequestParam("content") String content, // Yorum içeriği
                             @RequestParam(value = "imageUrl", required = false) String imageUrl) { // Resim URL'si (şimdilik basit tutalım)

        // Giriş yapmış kullanıcıyı bul
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        // Tarihi yeri bul
        HistoricalPlace place = placeService.getPlaceById(placeId)
                .orElseThrow(() -> new RuntimeException("Tarihi Yer bulunamadı!"));

        // Yeni yorum nesnesini oluştur
        Comment newComment = new Comment();
        newComment.setContent(content);
        newComment.setImageUrl(imageUrl); // Resim URL'sini kaydet
        
        // Yorumu kaydet
        commentService.saveComment(newComment, place, user);

        // Kullanıcıyı detay sayfasına geri yönlendir
        return "redirect:/place/" + placeId + "#comments";
    }
}