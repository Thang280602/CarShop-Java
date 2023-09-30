package com.otothang.controllers.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.otothang.Service.InformationShopSevice;
import com.otothang.Service.StorageService;
import com.otothang.models.Blog;
import com.otothang.models.InformationShop;

@Controller
@RequestMapping("/admin")
public class InformationShopController {
	@Autowired
	private InformationShopSevice informationShopSevice;
	@Autowired
	private StorageService seStorageService;
	@RequestMapping("/information")
	public String Information(Model model) {
		List<InformationShop> informationShops=this.informationShopSevice.getAll();
		model.addAttribute("infor", informationShops);
		return "/admin/imformationShop/index";
	}
	@GetMapping("/infor-add")
	public String add(Model model) {
		InformationShop informationShop = new InformationShop();
		model.addAttribute("informationShop", informationShop);
		return "admin/imformationShop/add";
	}

	@PostMapping("/infor-add")
	public String save(@ModelAttribute("informationShop") InformationShop informationShop, @RequestParam("fileImage") MultipartFile file) {
		this.seStorageService.store(file);
		String fileName = file.getOriginalFilename();
		informationShop.setLogo(fileName);
		if (this.informationShopSevice.create(informationShop)) {
			return "redirect:/admin/imformationShop";
		}
		return "admin/imformationShop/add";
	}
	@GetMapping("/edit-infor/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		InformationShop informationShop = this.informationShopSevice.findById(id);
		model.addAttribute("imformationShop", informationShop);
		return "admin/imformationShop/edit";
	}

	@PostMapping("/edit-infor")
	public String upddate(@ModelAttribute("imformationShop")InformationShop imformationShop,@RequestParam("fileImage") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		boolean isEmpty = fileName == null || fileName.trim().length() == 0;
		if (!isEmpty) {
			this.seStorageService.store(file);
			imformationShop.setLogo(fileName);
		}
		if (this.informationShopSevice.create(imformationShop)) {
			return "redirect:/admin/information";
		} else {
			return "admin/imformationShop/add";
		}
	}

}
