package com.otothang.Service;

import java.util.List;

import com.otothang.models.Category;
import com.otothang.models.Comment;

public interface CommentSevice {
	List<Comment> getAll();
	Boolean create(Comment comment);
	Comment findById(Integer id);
	Boolean delete(Integer id);
}
