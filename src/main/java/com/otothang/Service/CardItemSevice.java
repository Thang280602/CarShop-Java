package com.otothang.Service;

import java.util.List;

import com.otothang.models.CardItem;

public interface CardItemSevice {
	Boolean add(CardItem cardItem);
	Boolean update(CardItem cardItem);
	CardItem findId(Integer id);
	Boolean delete(Integer id);
	Boolean deleteByProductId(Integer productId);
	CardItem checkCardItem(Integer cardId,Integer productId);
	void deleteByCardId(Integer cardId);
	void updateCard(Integer quantity);
	
}
