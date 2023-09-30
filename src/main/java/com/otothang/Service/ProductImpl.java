package com.otothang.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.otothang.Repository.ProductRepository;
import com.otothang.models.Category;
import com.otothang.models.Product;
import com.otothang.models.SearchModel;

@Service
public class ProductImpl implements ProductSevice {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return this.productRepository.findAll();
	}

	@Override
	public Boolean create(Product product) {
		try {
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return this.productRepository.findById(id).get();
	}

	@Override
	public Boolean update(Product product) {
		// TODO Auto-generated method stub
		try {
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			this.productRepository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> findProductsByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return this.productRepository.findProductsByCategoryName(categoryName);
	}

	@Override
	public List<Product> findAllOrderByPriceAsc(String sortOrder) {
		// TODO Auto-generated method stub
		return this.productRepository.findAllOrderByPriceAsc(sortOrder);
	}

	@Override
	public List<Product> findAllOrderByPriceDESC(String sortOrder) {
		// TODO Auto-generated method stub
		return this.productRepository.findAllOrderByPriceDesc(sortOrder);
	}

	@Override
	public List<Product> getOrderByNew() {
		// TODO Auto-generated method stub
		return this.productRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
	}

	@Override
	public List<Product> findByPrice(Double price) {
		// TODO Auto-generated method stub
		return this.productRepository.findByPrice(price);
	}

	@Override
	public List<String> findAllDistinctModels() {
		// TODO Auto-generated method stub
		return this.productRepository.findAllDistinctModels();
	}

	@Override
	public List<String> findProductAddress() {
		// TODO Auto-generated method stub
		return this.productRepository.findProductAddress();
	}

	@Override
	public List<Product> SearchProduct(SearchModel searchModel) {
		String categoryName = searchModel.getCategoryName();
		String models = searchModel.getModel();
		String productAddress = searchModel.getProductAddress();
		String[] price = searchModel.getProductPrice().split("-");
		Double priceMin = Double.parseDouble(price[0]);
		Double priceMax = Double.parseDouble(price[1]);
		
		String sortId = searchModel.getSortId();
		return this.productRepository.SearchProduct(categoryName, models, productAddress, priceMin, priceMax, sortId);
	}

	@Override
	public List<Product> findByCategoryId(Integer categoryId) {
		
		return this.productRepository.findByCategoryId(categoryId);
	}

	@Override
	public List<Product> searchCategory(String keyword) {
		// TODO Auto-generated method stub
		return this.productRepository.searchCategory(keyword);
	}

	@Override
	public Page<Product> getAll(Integer pageNo) {
		PageRequest pageable = PageRequest.of(pageNo-1, 6);
		return this.productRepository.findAll(pageable);
	}

	@Override
	public Page<Product> searchProduct(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
