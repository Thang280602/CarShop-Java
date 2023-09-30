package com.otothang.controllers.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.otothang.Repository.ImageProductRepository;
import com.otothang.Service.CategorySevice;
import com.otothang.Service.ImageProductSevice;
import com.otothang.Service.ProductSevice;
import com.otothang.Service.StorageService;
import com.otothang.models.Category;
import com.otothang.models.ImageProduct;
import com.otothang.models.Product;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	private CategorySevice categorySevice;
	@Autowired
	private StorageService seStorageService;
	@Autowired
	private ProductSevice productSevice;
	@Autowired
	private ImageProductSevice imageProductSevice;

	@RequestMapping("/product")
	public String index(Model model,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
		Page<Product> listProduct=this.productSevice.getAll(pageNo);
		model.addAttribute("totalPage", listProduct.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("listProduct", listProduct);
		return "admin/product/index";
	}

	@RequestMapping("/product-add")
	public String add(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		List<Category> listcate = this.categorySevice.getAll();
		model.addAttribute("listcate", listcate);
		return "admin/product/add";
	}

	@PostMapping("/product-add")
	public String save(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file,
			@RequestParam("fileImages") MultipartFile[] files) {
		this.seStorageService.store(file);
		String fileName = file.getOriginalFilename();
		product.setImage(fileName);
		if (this.productSevice.create(product)) {
			for (MultipartFile multipartFile : files) {
				ImageProduct imageProduct = new ImageProduct();
				imageProduct.setImage(multipartFile.getOriginalFilename());
				imageProduct.setProduct(product);
				this.seStorageService.store(multipartFile);
				this.imageProductSevice.create(imageProduct);
			}
			return "redirect:/admin/product";
		}
		return "admin/product/add";
	}

	@GetMapping("/edit-product/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Product product = this.productSevice.findById(id);
		model.addAttribute("product", product);
		List<Category> listcate = this.categorySevice.getAll();
		model.addAttribute("listcate", listcate);
		List<String> imgDetail = new ArrayList<String>();
		for (ImageProduct imgPro : product.getImageProduct()) {
			imgDetail.add(imgPro.getImage());
		}
		model.addAttribute("imgDetail", imgDetail);
		return "admin/product/edit";
	}

	@PostMapping("/edit-product")
	public String upddate(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file,
			@RequestParam("fileImages") MultipartFile[] files) {
		String fileName = file.getOriginalFilename();
		boolean isEmpty = fileName == null || fileName.trim().length() == 0;
		if (!isEmpty) {
			this.seStorageService.store(file);
			product.setImage(fileName);
		}
		String fileName1 = files[0].getOriginalFilename();
		boolean isEmpty1 = fileName1 == null || fileName1.trim().length() == 0;
		if (!isEmpty1) {
			// xoa cac ban ghi
			this.imageProductSevice.deleteByProductId(product.getId());
			for (MultipartFile multipartFile : files) {
				// upload moi
				ImageProduct imageProduct = new ImageProduct();
				imageProduct.setImage(multipartFile.getOriginalFilename());
				imageProduct.setProduct(product);
				this.seStorageService.store(multipartFile);
				this.imageProductSevice.create(imageProduct);
			}
		}
		if (this.productSevice.create(product)) {
			return "redirect:/admin/product";
		} else {
			return "admin/product/add";
		}
	}

	@GetMapping("/delete-product/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.imageProductSevice.deleteByProductId(id);
		if (this.productSevice.delete(id)) {
			return "redirect:/admin/product";
		} else {
			return "redirect:/admin/product";
		}
	}
}
