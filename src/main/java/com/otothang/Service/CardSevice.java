package com.otothang.Service;

import java.util.List;

import com.otothang.models.Card;
import com.otothang.models.CardItem;
import com.otothang.models.User;

public interface CardSevice {
	Boolean addCard(Card card);
	Long check(Long idUser);
	Card findByUser(User user);
	List<CardItem> findByCartId(Integer cartId);
}
