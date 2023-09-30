package com.otothang.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.otothang.Service.OrderDetailSevice;
import com.otothang.Service.OrderSevice;
import com.otothang.models.Order;
import com.otothang.models.OrderDetail;

@Controller
@RequestMapping("/admin")
public class OrderAdminController {
	@Autowired
	private OrderSevice orderSevice;
	@Autowired
	private OrderDetailSevice orderDetail;

	@RequestMapping("/order")
	public String orders(Model model) {
		List<Order> order = this.orderSevice.getAll();
		model.addAttribute("orders", order);
		return "admin/order/index";
	}

	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer idCart) {
		Order order = this.orderSevice.findById(idCart);
		List<OrderDetail> list = this.orderDetail.getByOrderId(order);
		model.addAttribute("order", order);
		model.addAttribute("list", list);
		return "admin/order/detail";
	}

	@RequestMapping("/updateOrder")
	public String update(@RequestParam("id") Integer idOrder, @RequestParam("status") Integer status) {
		Order order = this.orderSevice.findById(idOrder);
		order.setStatus(status);
		this.orderSevice.create(order);
		
		return "redirect:/admin/order";
	}
}
