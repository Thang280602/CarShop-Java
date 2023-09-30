package com.otothang.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.otothang.models.Card;
import com.otothang.models.User;

import java.util.List;


public interface CardRepository extends JpaRepository<Card, Integer>{
	@Query("SELECT COUNT(*) FROM Card c WHERE c.user.id=?1")
	Long countId(Long idUser);
	List<Card> findByUser(User user);
}
