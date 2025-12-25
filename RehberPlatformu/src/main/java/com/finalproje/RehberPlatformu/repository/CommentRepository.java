package com.finalproje.RehberPlatformu.repository;

import com.finalproje.RehberPlatformu.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}