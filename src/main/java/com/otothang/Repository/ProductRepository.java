package com.otothang.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otothang.models.Category;
import com.otothang.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.category.categoryName = :categoryName")
	List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);
	
	@Query("SELECT c FROM Product c where c.productName like %?1%")
	List<Product> searchCategory(String keyword);
	
	@Query("SELECT p FROM Product p ORDER BY p.price ASC")
	List<Product> findAllOrderByPriceAsc(String sortOrder);

	@Query("SELECT p FROM Product p ORDER BY p.price DESC")
	List<Product> findAllOrderByPriceDesc(String sortOrder);

	@Query("SELECT p FROM Product p WHERE p.price <= :price")
	List<Product> findByPrice(@Param("price") Double price);

	@Query("SELECT DISTINCT p.models FROM Product p")
    List<String> findAllDistinctModels();
	
	@Query("SELECT DISTINCT p.productAddress FROM Product p")
    List<String> findProductAddress();
	
	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Integer categoryId);
	
	@Query("SELECT p FROM Product p WHERE p.category.categoryName = :categoryName and p.models=:models and p.productAddress =:productAddress and p.price >= :priceMin and p.price <= :priceMax ORDER BY CASE WHEN :sortId = 'ASC' THEN p.id END ASC, CASE WHEN :sortId = 'DESC' THEN p.id END DESC")
	List<Product> SearchProduct(@Param("categoryName") String categoryName, @Param("models") String models, @Param("productAddress") String productAddress, @Param("priceMin") Double priceMin,@Param("priceMax") Double priceMax , @Param("sortId") String sortId);
}
