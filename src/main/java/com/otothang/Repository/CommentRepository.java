package com.otothang.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otothang.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
