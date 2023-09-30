package com.otothang.Service;

import com.otothang.models.Blog;
import com.otothang.models.ImageProduct;
import com.otothang.models.InformationShop;

public interface ImageProductSevice {
	Boolean create(ImageProduct imageProduct);
	Boolean deleteByProductId(Integer id);

}
