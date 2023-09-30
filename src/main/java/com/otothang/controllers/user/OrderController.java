package com.otothang.controllers.user;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.otothang.Service.BlogService;
import com.otothang.Service.CardItemSevice;
import com.otothang.Service.CardSevice;
import com.otothang.Service.CategorySevice;
import com.otothang.Service.OrderDetailSevice;
import com.otothang.Service.OrderSevice;
import com.otothang.Service.ProductSevice;
import com.otothang.models.Blog;
import com.otothang.models.Card;
import com.otothang.models.CardItem;
import com.otothang.models.Category;
import com.otothang.models.CustomUserDetails;
import com.otothang.models.Order;
import com.otothang.models.OrderDetail;

@Controller
public class OrderController {
	@Autowired
	private CardItemSevice cardItemSevice;
	@Autowired
	private CardSevice cardSevice;
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private OrderSevice orderSevice;
	@Autowired
	private OrderDetailSevice orderDetailSevice;
	@Autowired
	private CategorySevice categorySevice;
	@Autowired
	private BlogService blogService;
	@RequestMapping("/checkout")
	public String checkout(Principal principal, Model model) {
		if (principal == null) {
			return "/user/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Card card = this.cardSevice.findByUser(customUserDetails.getUser());
		model.addAttribute("user", customUserDetails.getUser());
		
		model.addAttribute("listCard", card);
		List<Category> categories1=categorySevice.getAll();
		model.addAttribute("cate1", categories1);
		Order order = new Order();
		order.setUser(customUserDetails.getUser());
		model.addAttribute("order", order);
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/checkout";
	}

	@PostMapping("/postCheckout")
	public String postCheckout(Model model,Principal principal, @ModelAttribute("order") Order order) {
		if (principal == null) {
			return "/user/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Card card = this.cardSevice.findByUser(customUserDetails.getUser());
		order.setUser(customUserDetails.getUser());
		order.setCreateAt(new Date());
		order.setStatus(0);
		if (this.orderSevice.create(order)) {
			for (CardItem cardItem : card.getCardItems()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder(order);
				orderDetail.setPrice(cardItem.getProduct().getPrice());
				orderDetail.setProduct(cardItem.getProduct());
				orderDetail.setQuantity(cardItem.getQuantity());
				this.orderDetailSevice.add(orderDetail);

			}
		}
		List<Category> categories1=categorySevice.getAll();
		model.addAttribute("cate1", categories1);
		this.cardItemSevice.deleteByCardId(card.getId());
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "index";
	}
}
