package com.otothang.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "card")
public class Card {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	@JoinColumn(name="userId",referencedColumnName = "id")
	private User user;
	@OneToMany(mappedBy = "card",fetch = FetchType.EAGER)
	private Set<CardItem> cardItems;
	public Card() {
		super();
	}
	public Card(Integer id, User user, Set<CardItem> cardItems) {
		super();
		this.id = id;
		this.user = user;
		this.cardItems = cardItems;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<CardItem> getCardItems() {
		return cardItems;
	}
	public void setCardItems(Set<CardItem> cardItems) {
		this.cardItems = cardItems;
	}
	
}
