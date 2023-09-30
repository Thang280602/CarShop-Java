package com.otothang.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.otothang.Service.UserService;
import com.otothang.models.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService service;
	@GetMapping
	public String index() {
		return "redirect:/admin/";
	}

	@RequestMapping("/")
	public String admin() {
		return "admin/index";
	}
	@RequestMapping("/account")
	public String Accout(Model model) {
		List<User> user=this.service.getALL();
		model.addAttribute("user", user);
		return "admin/account/index";
	}
}
