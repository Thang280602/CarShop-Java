package com.otothang.controllers.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.otothang.Service.BlogService;
import com.otothang.Service.StorageService;
import com.otothang.models.Blog;
import com.otothang.models.Category;
import com.otothang.models.Staff;

@Controller
@RequestMapping("/admin")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private StorageService seStorageService;

	@GetMapping("/blog")
	public String Index(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Blog> list = this.blogService.getAll(pageNo);
		if (keyword != null) {
			list = this.blogService.searchCategory(keyword, pageNo);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("list", list);
		return "admin/blog/index";
	}

	@GetMapping("/blog-add")
	public String add(Model model) {
		Blog blog = new Blog();
		blog.setDate(new Date());
		model.addAttribute("blog", blog);
		return "admin/blog/add";
	}

	@PostMapping("/blog-add")
	public String save(@ModelAttribute("blog") Blog blog, @RequestParam("fileImage") MultipartFile file) {
		this.seStorageService.store(file);
		String fileName = file.getOriginalFilename();
		blog.setBlogImage(fileName);
		blog.setDate(new Date());
		if (this.blogService.create(blog)) {
			return "redirect:/admin/blog";
		}
		return "admin/blog/add";
	}

	@GetMapping("/edit-blog/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Blog blog = this.blogService.findById(id);
		model.addAttribute("blog", blog);
		return "admin/blog/edit";
	}

	@PostMapping("/edit-blog")
	public String upddate(@ModelAttribute("blog") Blog blog,@RequestParam("fileImage") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		boolean isEmpty = fileName == null || fileName.trim().length() == 0;
		if (!isEmpty) {
			this.seStorageService.store(file);
			blog.setBlogImage(fileName);
			blog.setDate(new Date());
		}
		if (this.blogService.create(blog)) {
			return "redirect:/admin/blog";
		} else {
			return "admin/blog/add";
		}
	}

	@GetMapping("/delete-blog/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.blogService.delete(id);
		return "redirect:/admin/blog";
	}
}
