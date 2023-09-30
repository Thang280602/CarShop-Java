package com.otothang.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.otothang.Repository.ProductRepository;
import com.otothang.Service.BlogService;
import com.otothang.Service.CategorySevice;
import com.otothang.Service.ProductSevice;
import com.otothang.models.Blog;
import com.otothang.models.Category;
import com.otothang.models.Product;
import com.otothang.models.SearchModel;

@Controller
@RequestMapping("/user")
public class ListingClassic {
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategorySevice categorySevice;
	@Autowired
	private BlogService blogService;
	@RequestMapping("/ListingClassic")
	public String About(Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo ) {
		Page<Product> products=this.productSevice.getAll(pageNo);
		model.addAttribute("totalPage", products.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("product", products);
		List<Category> categories=categorySevice.findByCategoryStatus(true);
		model.addAttribute("listCate", categories);
		List<Product> productAll=productSevice.getAll();
		model.addAttribute("productALL", productAll);
		List<String> models=this.productSevice.findAllDistinctModels();
		model.addAttribute("model", models);
		List<Category> categories1=categorySevice.getAll();
		model.addAttribute("cate1", categories1);
		List<String> productAddress=this.productSevice.findProductAddress();
		model.addAttribute("productAddress", productAddress);
		model.addAttribute("searchModel", new SearchModel());
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "/user/ListingClassic";
	}

	@GetMapping("/ListingClassic/Search")
	public String searchProducts(@RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder,
			Model model) {
		if ("asc".equals(sortOrder)) {
			List<Product> products=this.productSevice.findAllOrderByPriceAsc(sortOrder);
			model.addAttribute("products", products);
			List<Category> categories=categorySevice.getAll();
			model.addAttribute("listCate", categories);
			List<String> models=this.productSevice.findAllDistinctModels();
			model.addAttribute("model", models);
			List<String> productAddress=this.productSevice.findProductAddress();
			model.addAttribute("productAddress", productAddress);
			List<Blog> blog=this.blogService.getAll();
			model.addAttribute("blog", blog);
			return "/user/ListingClassicSX";
		} else if ("desc".equals(sortOrder)) {
			List<Product> productss=this.productSevice.findAllOrderByPriceDESC(sortOrder);	
			model.addAttribute("productss", productss);
			List<Category> categories=categorySevice.getAll();
			model.addAttribute("listCate", categories);
			List<String> models=this.productSevice.findAllDistinctModels();
			model.addAttribute("model", models);
			List<String> productAddress=this.productSevice.findProductAddress();
			model.addAttribute("productAddress", productAddress);
			List<Blog> blog=this.blogService.getAll();
			model.addAttribute("blog", blog);
			return "/user/ListingClassicSX2";
		} else if ("new".equals(sortOrder)) {
			List<Product> productsss=this.productSevice.getOrderByNew();
			model.addAttribute("productsss", productsss);
			List<Category> categories=categorySevice.getAll();
			model.addAttribute("listCate", categories);
			List<String> models=this.productSevice.findAllDistinctModels();
			model.addAttribute("model", models);
			List<String> productAddress=this.productSevice.findProductAddress();
			model.addAttribute("productAddress", productAddress);
			List<Blog> blog=this.blogService.getAll();
			model.addAttribute("blog", blog);
			return "/user/ListingClassicSX3";
		} else {
			return "redirect:/user/ListingClassic";
			
		}
	}
	@PostMapping("/fillProduct1")
	public String SearchProduct(Model model, @ModelAttribute("searchModel") SearchModel searchModel,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
		List<Product> listProduct = this.productSevice.SearchProduct(searchModel);
		model.addAttribute("product1", listProduct);
		List<Category> categories=categorySevice.getAll();
		model.addAttribute("listCate", categories);
		List<String> models=this.productSevice.findAllDistinctModels();
		model.addAttribute("model", models);
		List<String> productAddress=this.productSevice.findProductAddress();
		model.addAttribute("productAddress", productAddress);
		model.addAttribute("searchModel", new SearchModel());
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "user/ListingClassic4";
	}
	@PostMapping("/fillProduct2")
	public String SearchProduct1(Model model, @ModelAttribute("searchModel") SearchModel searchModel,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
		List<Product> listProduct = this.productSevice.SearchProduct(searchModel);
		model.addAttribute("product1", listProduct);
		List<Category> categories=categorySevice.getAll();
		model.addAttribute("listCate", categories);
		List<String> models=this.productSevice.findAllDistinctModels();
		model.addAttribute("model", models);
		List<String> productAddress=this.productSevice.findProductAddress();
		model.addAttribute("productAddress", productAddress);
		model.addAttribute("searchModel", new SearchModel());
		List<Blog> blog=this.blogService.getAll();
		model.addAttribute("blog", blog);
		return "user/ListingClassic4";
	}
}
