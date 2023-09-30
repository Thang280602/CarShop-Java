package com.otothang.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otothang.Repository.ImageProductRepository;
import com.otothang.models.ImageProduct;

@Service
public class ImageProductSeviceImpl implements ImageProductSevice{
	@Autowired
	public ImageProductRepository imageProductRepository;
	@Override
	public Boolean create(ImageProduct imageProduct) {
		try {
			this.imageProductRepository.save(imageProduct);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Boolean deleteByProductId(Integer id) {
		try {
			this.imageProductRepository.deleteByProductId(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
