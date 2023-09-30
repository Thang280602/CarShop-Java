package com.otothang.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.otothang.Service.BlogService;
import com.otothang.Service.CategorySevice;
import com.otothang.Service.StaffSevice;
import com.otothang.models.Blog;
import com.otothang.models.Category;
import com.otothang.models.Staff;

@Controller
@RequestMapping("/user")
public class AboutUsController {
	@Autowired
	private StaffSevice staffSevice;
	@Autowired
	private CategorySevice categorySevice;
	@Autowired
	private BlogService blogService;
	@RequestMapping("/AboutUs")
	public String About(Model model) {
		List<Category> categories=categorySevice.getAll();
		model.addAttribute("listCate", categories);
		List<Staff> staff=this.staffSevice.getAll();
		model.addAttribute("staff", staff);
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/AboutUs";
	}
}
