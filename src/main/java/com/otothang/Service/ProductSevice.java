package com.otothang.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.otothang.models.Category;
import com.otothang.models.Product;
import com.otothang.models.SearchModel;

public interface ProductSevice {
	List<Product> getAll();

	List<Product> getOrderByNew();

	Boolean create(Product product);

	Product findById(Integer id);
	
	List<Product> searchCategory(String keyword);
	
	List<Product> findByCategoryId(Integer categoryId);

	Boolean update(Product product);

	Boolean delete(Integer id);

	List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);

	List<Product> findAllOrderByPriceAsc(String sortOrder);

	List<Product> findAllOrderByPriceDESC(String sortOrder);

	List<Product> findByPrice(@Param("price") Double price);

	List<String> findAllDistinctModels();

	List<String> findProductAddress();

	List<Product> SearchProduct(SearchModel searchModel);
	Page<Product> getAll(Integer pageNo);
	Page<Product> searchProduct(String keyword,Integer pageNo);
}
