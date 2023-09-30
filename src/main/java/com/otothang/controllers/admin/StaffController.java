package com.otothang.controllers.admin;

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
import org.springframework.web.multipart.MultipartFile;

import com.otothang.Service.StaffSevice;
import com.otothang.Service.StorageService;
import com.otothang.models.ImageProduct;
import com.otothang.models.Product;
import com.otothang.models.Staff;

@Controller
@RequestMapping("/admin")
public class StaffController {
	@Autowired
	private StaffSevice staffSevice; 
	@Autowired
	private StorageService seStorageService;
	@GetMapping("/staff")
	public String index(Model model,@Param("keyword") String keyword,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
		Page<Staff>  list=this.staffSevice.getAll(pageNo);
		if(keyword!=null) {
			list=this.staffSevice.searchStaff(keyword, pageNo);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("list", list);
		return "admin/staff/index";
	}
	@GetMapping("/staff-add")
	public String add(Model model) {
		Staff staff =new Staff();
		model.addAttribute("staff", staff);
		return "admin/staff/add";
	}
	@PostMapping("/staff-add")
	public String save(@ModelAttribute("staff") Staff staff, @RequestParam("fileImage") MultipartFile file) {
		this.seStorageService.store(file);
		String fileName = file.getOriginalFilename();
		staff.setImage(fileName);
		if (this.staffSevice.create(staff)) {
			return "redirect:/admin/staff";
			}
		 return "admin/staff/add";
		}
		
	@GetMapping("/edit-staff/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		Staff staff =this.staffSevice.findById(id);
		model.addAttribute("staff", staff);
		return "admin/staff/edit";
	}
	@PostMapping("/edit-staff")
	public String upddate(@ModelAttribute("staff") Staff staff, @RequestParam("fileImage") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		boolean isEmpty = fileName == null || fileName.trim().length() == 0;
		if(!isEmpty) {
			this.seStorageService.store(file);
			staff.setImage(fileName);
		}
		if (this.staffSevice.create(staff)) {
			return "redirect:/admin/staff";
		}
		return "admin/staff/add";
	}
	@GetMapping("/delete-staff/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.staffSevice.delete(id);
		return "redirect:/admin/staff";
	}
	
}
