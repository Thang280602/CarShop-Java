package com.otothang.controllers.admin;

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

import com.otothang.Service.CategorySevice;
import com.otothang.Service.ImageProductSevice;
import com.otothang.Service.ProductSevice;
import com.otothang.models.Category;


@Controller
@RequestMapping("/admin")
public class CategoryController {
	@Autowired
	private CategorySevice categorySevice; 
	@Autowired
	private ProductSevice productSevice;
	@Autowired 
	private ImageProductSevice imageProductSevice;
	@GetMapping("/category")
	public String index(Model model,@Param("keyword") String keyword,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
		Page<Category>  list=this.categorySevice.getAll(pageNo);
		if(keyword!=null) {
			list=this.categorySevice.searchCategory(keyword, pageNo);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("list", list);
		return "admin/category/index";
	}
	@GetMapping("/add-category")
	public String add(Model model) {
		Category category =new Category();
		category.setCategoryStatus(true);
		model.addAttribute("category", category);
		return "admin/category/add";
	}
	@PostMapping("/add-category")
	public String save(@ModelAttribute("category") Category category) {
		if(this.categorySevice.create(category)) {
			return "redirect:/admin/category";
		}else {
			return "admin/category/add";
		}
		
	}
	@GetMapping("/edit-category/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		Category category =this.categorySevice.findById(id);
		model.addAttribute("category", category);
		return "admin/category/edit";
	}
	@PostMapping("/edit-category")
	public String upddate(@ModelAttribute("category") Category category) {
		if(this.categorySevice.create(category)) {
			return "redirect:/admin/category";
		}else {
			return "admin/category/add";
		}
	}
	@GetMapping("/delete-category/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.imageProductSevice.deleteByProductId(id);
		this.productSevice.delete(id);
		if(this.categorySevice.delete(id)) {
			return "redirect:/admin/category";
		}else {
			return "redirect:/admin/category";
		}
	}
}
