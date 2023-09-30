package com.otothang.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.otothang.Repository.StaffRepository;
import com.otothang.models.Category;
import com.otothang.models.Staff;

@Service
public class StaffSeviceImpl implements StaffSevice{
	@Autowired
	private StaffRepository staffRepository;
	@Override
	public List<Staff> getAll() {
		// TODO Auto-generated method stub
		return this.staffRepository.findAll();
	}

	@Override
	public Boolean create(Staff staff) {
		try {
			this.staffRepository.save(staff);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Staff findById(Integer id) {
		// TODO Auto-generated method stub
		return this.staffRepository.findById(id).get();
	}

	@Override
	public Boolean update(Staff staff) {
		try {
			this.staffRepository.save(staff);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			this.staffRepository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Staff> searchStaff(String keyword) {
		// TODO Auto-generated method stub
		return this.staffRepository.searchStaff(keyword);
	}

	@Override
	public Page<Staff> getAll(Integer pageNo) {
		// TODO Auto-generated method stub
		PageRequest pageable = PageRequest.of(pageNo-1, 2);
		return this.staffRepository.findAll(pageable);
	}

	@Override
	public Page<Staff> searchStaff(String keyword, Integer pageNo) {
		List list=this.staffRepository.searchStaff(keyword);
		PageRequest pageable=PageRequest.of(pageNo-1, 2);
		Integer start=(int) pageable.getOffset();
		Integer end=(int) (pageable.getOffset()+pageable.getPageSize()>list.size()?list.size():pageable.getOffset()+pageable.getPageSize());
		list=list.subList(start, end);
		return new PageImpl<Staff>(list,pageable, this.searchStaff(keyword).size());
	}

}
