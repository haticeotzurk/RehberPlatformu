package com.finalproje.RehberPlatformu.service;

import com.finalproje.RehberPlatformu.entity.Comment;
import com.finalproje.RehberPlatformu.entity.HistoricalPlace;
import com.finalproje.RehberPlatformu.entity.User;
import com.finalproje.RehberPlatformu.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    public Comment saveComment(Comment comment, HistoricalPlace place, User user) {
        comment.setPlace(place);
        comment.setUser(user);
        
        return commentRepository.save(comment);
    }
}