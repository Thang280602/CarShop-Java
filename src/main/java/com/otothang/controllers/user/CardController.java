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
import org.springframework.web.bind.annotation.RequestParam;

import com.otothang.Repository.CardItemRepository;
import com.otothang.Service.BlogService;
import com.otothang.Service.CardItemSevice;
import com.otothang.Service.CardSevice;
import com.otothang.Service.CategorySevice;
import com.otothang.Service.ProductSevice;
import com.otothang.models.Blog;
import com.otothang.models.Card;
import com.otothang.models.CardItem;
import com.otothang.models.Category;
import com.otothang.models.CustomUserDetails;

@Controller
public class CardController {
	@Autowired
	private CardItemSevice cardItemSevice;
	@Autowired
	private CardSevice cardSevice;
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private CategorySevice categorySevice;
	@Autowired
	private BlogService blogService;
	@GetMapping("/user/card")
	public String showCart(Principal principal, Model model) {
		if (principal == null) {

			return "/user/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Card card = this.cardSevice.findByUser(customUserDetails.getUser());
		model.addAttribute("listCard", card);
		List<Category> categories1=categorySevice.getAll();
		model.addAttribute("cate1", categories1);
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/Card";
	}

	@PostMapping("/user/card")
	public String addCart(Model model,@RequestParam("id") Integer idProduct, Principal principal) {
		if (principal == null) {
			return "/user/login";
		}

		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		// Kiểm tra ID của User
		if (customUserDetails != null && customUserDetails.getUser() != null) {
			// Kiểm tra xem User đã có Card chưa
			if (this.cardSevice.check(customUserDetails.getUser().getId()) == 0) {
				Card card = new Card();
				card.setUser(customUserDetails.getUser());
				this.cardSevice.addCard(card); // Lưu Card vào cơ sở dữ liệu
			}

			Card card1 = this.cardSevice.findByUser(customUserDetails.getUser());

			CardItem cardItem = new CardItem();

			cardItem.setCard(card1);
			CardItem checkCardItem = this.cardItemSevice.checkCardItem(cardItem.getCard().getId(), idProduct);
			if (checkCardItem != null) {
				checkCardItem.setQuantity(checkCardItem.getQuantity() + 1);
				this.cardItemSevice.updateCard(checkCardItem.getQuantity());
			} else {
				cardItem.setProduct(this.productSevice.findById(idProduct));
				cardItem.setQuantity(1); // Đặt quantity thành 1 ban đầu
				this.cardItemSevice.add(cardItem);
			}

			return "redirect:/user/card";
		}
		return "/user/login";
	}

	@RequestMapping("/user/card-delete")
	public String deleteCartItem(@RequestParam("id") Integer id) {
		this.cardItemSevice.delete(id);
		return "redirect:/user/card";
	}
	@GetMapping("/user/cardMy")
	public String showCart1(Principal principal, Model model) {
		if (principal == null) {

			return "/user/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Card card = this.cardSevice.findByUser(customUserDetails.getUser());
		model.addAttribute("listCard", card);
		List<Category> categories1=categorySevice.getAll();
		model.addAttribute("cate1", categories1);
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/Card";
	}

}
