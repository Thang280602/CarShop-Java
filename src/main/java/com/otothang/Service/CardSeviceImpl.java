package com.otothang.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otothang.Repository.CardRepository;
import com.otothang.models.Card;
import com.otothang.models.CardItem;
import com.otothang.models.User;
@Service
public class CardSeviceImpl implements CardSevice{
	@Autowired
	private CardRepository cardRepository;
	@Override
	public Boolean addCard(Card card) {
		try {
			this.cardRepository.save(card);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Long check(Long idUser) {
		// TODO Auto-generated method stub
		return this.cardRepository.countId(idUser);
	}
	@Override
	public Card findByUser(User user) {
		// TODO Auto-generated method stub
		return this.cardRepository.findByUser(user).get(0);
	}
	@Override
	public List<CardItem> findByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}

}
