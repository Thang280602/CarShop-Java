package com.otothang.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.otothang.Repository.BlogRepository;
import com.otothang.models.Blog;
import com.otothang.models.Category;
@Service
public class BlogServiceImpl implements BlogService{
	@Autowired
	private BlogRepository blogRepository;
	@Override
	public List<Blog> getAll() {
		// TODO Auto-generated method stub
		return this.blogRepository.findAll();
	}

	@Override
	public Boolean create(Blog blog) {
		try {
			this.blogRepository.save(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Blog findById(Integer id) {
		return this.blogRepository.findById(id).get();
	}

	@Override
	public Boolean update(Blog blog) {
		try {
			this.blogRepository.save(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			this.blogRepository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Blog> searchCategory(String keyword) {
		// TODO Auto-generated method stub
		return this.blogRepository.searchCategory(keyword);
	}

	@Override
	public Page<Blog> getAll(Integer pageNo) {
		PageRequest pageable = PageRequest.of(pageNo-1, 2);
		return this.blogRepository.findAll(pageable);
	}

	@Override
	public Page<Blog> searchCategory(String keyword, Integer pageNo) {
		List list=this.blogRepository.searchCategory(keyword);
		PageRequest pageable=PageRequest.of(pageNo-1, 2);
		Integer start=(int) pageable.getOffset();
		Integer end=(int) (pageable.getOffset()+pageable.getPageSize()>list.size()?list.size():pageable.getOffset()+pageable.getPageSize());
		list=list.subList(start, end);
		return new PageImpl<Blog>(list,pageable, this.searchCategory(keyword).size());
	}

}
