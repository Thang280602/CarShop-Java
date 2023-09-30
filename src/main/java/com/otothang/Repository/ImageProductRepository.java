package com.otothang.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.otothang.models.ImageProduct;

import jakarta.transaction.Transactional;

@Transactional
public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {
	@Modifying
	@Query("Delete from ImageProduct i Where i.product.id=?1")
	void deleteByProductId(Integer id);
}
