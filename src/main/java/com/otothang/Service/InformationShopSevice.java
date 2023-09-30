package com.otothang.Service;

import java.util.List;

import com.otothang.models.Blog;
import com.otothang.models.InformationShop;

public interface InformationShopSevice {
	List<InformationShop> getAll();
	Boolean create(InformationShop informationShop);
	InformationShop findById(Integer id);
}
