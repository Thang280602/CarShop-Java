package com.otothang.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.otothang.models.Staff;

public interface StaffSevice {
	List<Staff> getAll();
	Boolean create(Staff staff);
	Staff findById(Integer id);
	Boolean update (Staff staff);
	Boolean delete(Integer id);
	List<Staff> searchStaff(String keyword);
	Page<Staff> getAll(Integer pageNo);
	Page<Staff> searchStaff(String keyword,Integer pageNo);
}
