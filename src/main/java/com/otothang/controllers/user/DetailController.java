package com.otothang.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.otothang.Service.BlogService;
import com.otothang.Service.CategorySevice;
import com.otothang.Service.ProductSevice;
import com.otothang.models.Blog;
import com.otothang.models.Category;
import com.otothang.models.ImageProduct;
import com.otothang.models.Product;

@Controller
@RequestMapping("/user")
public class DetailController {
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private CategorySevice categorySevice;
	@Autowired
	private BlogService blogService;
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
	    Product product = this.productSevice.findById(id);	    
		model.addAttribute("product", product);
		List<Product> pro=this.productSevice.getAll();
		List<Product> pro1=new ArrayList<Product>();
		List<Category> categories1=categorySevice.getAll();
		model.addAttribute("cate1", categories1);
		for (Product product2 : pro) {
			if(Double.compare(product2.getPrice(), product.getPrice()) == 0) {
				pro1.add(product2);
			}
		}
		model.addAttribute("products", pro1);
		List<String> imgDetail = new ArrayList<String>();
		for (ImageProduct imgPro : product.getImageProduct()) {
		    imgDetail.add(imgPro.getImage());
		}
		model.addAttribute("imgDetail", imgDetail);
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/detail";
	}
}
