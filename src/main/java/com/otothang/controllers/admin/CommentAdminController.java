package com.otothang.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.otothang.Service.CommentSevice;
import com.otothang.models.Comment;
@Controller
@RequestMapping("/admin")
public class CommentAdminController {
	@Autowired
	private CommentSevice commentSevice;
	@RequestMapping("/comment")
	private String Comment(Model model) {
		List<Comment> comment=this.commentSevice.getAll()	;
		model.addAttribute("comment", comment);
		return "/admin/comment/index";
	}
	@GetMapping("/delete-comment/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.commentSevice.delete(id);
		return "redirect:/admin/comment";
	}
	
}
