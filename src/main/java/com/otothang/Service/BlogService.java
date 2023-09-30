package com.otothang.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.otothang.models.Blog;

public interface BlogService {
	List<Blog> getAll();

	Boolean create(Blog blog);

	Blog findById(Integer id);

	Boolean update(Blog blog);

	Boolean delete(Integer id);

	List<Blog> searchCategory(String keyword);

	Page<Blog> getAll(Integer pageNo);

	Page<Blog> searchCategory(String keyword, Integer pageNo);
}
