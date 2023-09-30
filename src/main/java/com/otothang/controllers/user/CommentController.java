package com.otothang.controllers.user;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.otothang.Service.BlogService;
import com.otothang.Service.CommentSevice;
import com.otothang.models.Blog;
import com.otothang.models.Comment;
import com.otothang.models.CustomUserDetails;

@Controller
public class CommentController {
	@Autowired
	private CommentSevice commentSevice;
	@Autowired
	private BlogService blogService;
	@RequestMapping("/user/comment")
	private String indexComment(Principal principal, Model model) {
		if (principal == null) {
			return "redirect:/user/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Comment comment1 = new Comment();
		comment1.setUser(customUserDetails.getUser());
		model.addAttribute("comment", comment1);
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/Comment";
	}

	@PostMapping("/user/commentSeach")
	private String Comment(@ModelAttribute("comment") Comment comment, Principal principal) {
		if (principal == null) {
			return "redirect:/user/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		comment.setUser(customUserDetails.getUser());
		this.commentSevice.create(comment);
		return "redirect:/user/comment";
	}
}
