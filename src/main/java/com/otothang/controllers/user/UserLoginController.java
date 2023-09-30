package com.otothang.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.otothang.Service.BlogService;
import com.otothang.Service.CategorySevice;
import com.otothang.Service.UserService;
import com.otothang.models.Blog;
import com.otothang.models.Category;
import com.otothang.models.User;

@Controller
public class UserLoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private CategorySevice categorySevice;
	@Autowired
	private BlogService blogService;
	@RequestMapping("/login")
	public String login( Model model) {
		List<Category> categories=categorySevice.getAll();
		model.addAttribute("listCate", categories);
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/login";
	}
	@RequestMapping("/register")
	public String register(Model model) {
		User user=new User();
		model.addAttribute("user", user);
		List<Category> categories=categorySevice.getAll();
		model.addAttribute("listCate", categories);
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
;		return "/user/register";
	}
	@PostMapping("/register")
	public String doRegister(@ModelAttribute("user") User user) {
		String hasPass= new BCryptPasswordEncoder().encode(user.getPassWord());
		user.setPassWord(hasPass);
		user.setEnabled(true);
		this.userService.create(user);

		return "/user/login";
	}
}
