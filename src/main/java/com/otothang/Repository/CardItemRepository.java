package com.otothang.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.otothang.models.CardItem;

import jakarta.transaction.Transactional;
@Transactional
public interface CardItemRepository extends JpaRepository<CardItem, Integer>{
	CardItem findByCardIdAndProductId(Integer cardId, Integer productId);
	Boolean deleteByProductId(Integer productId);
	@Modifying
	@Query("Delete from CardItem c Where c.car"
			+ "d.id=?1")
	void deleteByCardId(Integer cardId);
	@Modifying
	@Query("Update CardItem c set c.quantity =:quantity")
	void updateCard(Integer quantity);
}
