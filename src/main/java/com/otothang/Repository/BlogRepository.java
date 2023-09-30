package com.otothang.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.otothang.models.Blog;
import com.otothang.models.Category;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	@Query("SELECT c FROM Blog c where c.blogName like %?1%")
	List<Blog> searchCategory(String keyword);
}
