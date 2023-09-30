package com.otothang.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.otothang.Service.BlogService;
import com.otothang.Service.CategorySevice;
import com.otothang.Service.ProductSevice;
import com.otothang.models.Blog;
import com.otothang.models.Category;
import com.otothang.models.ImageProduct;
import com.otothang.models.Product;

@Controller
@RequestMapping("/user")
public class ListViewController {
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private CategorySevice categorySevice;
	@Autowired
	private BlogService blogService;
	@RequestMapping("/listView")
	private String ListView( Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
		Page<Product> product=this.productSevice.getAll(pageNo);
		model.addAttribute("totalPage", product.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pro", product);
		List<Category> categories=categorySevice.getAll();
		model.addAttribute("listCate1", categories);
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/ListView";
	}
	@RequestMapping("/index2/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		List<Category> categories=categorySevice.getAll();
		model.addAttribute("listCate1", categories);
		List<Product> product=productSevice.getAll();
		model.addAttribute("pro", product);
		List<String> models=this.productSevice.findAllDistinctModels();
		model.addAttribute("model", models);
		List<String> productAddress=this.productSevice.findProductAddress();
		model.addAttribute("productAddress", productAddress);
	    List<Product> pro2=this.productSevice.findByCategoryId(id);
	    model.addAttribute("pro2", pro2);
	    List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/index2";
	}
}
