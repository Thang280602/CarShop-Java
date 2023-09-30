package com.otothang.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otothang.Repository.InformationShopRepository;
import com.otothang.models.Blog;
import com.otothang.models.InformationShop;

@Service
public class InformationShopSeviceImpl implements InformationShopSevice{
	@Autowired
	private InformationShopRepository informationShopRepository;
	

	@Override
	public Boolean create(InformationShop informationShop) {
		try {
			this.informationShopRepository.save(informationShop);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<InformationShop> getAll() {
		// TODO Auto-generated method stub
		return this.informationShopRepository.findAll();
	}


	@Override
	public InformationShop findById(Integer id) {
		// TODO Auto-generated method stub
		return this.informationShopRepository.findById(id).get();
	}

}
