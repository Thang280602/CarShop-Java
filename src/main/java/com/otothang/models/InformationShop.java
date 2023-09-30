package com.otothang.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "informationShop")
public class InformationShop {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name =  "telephone",length=4000)
	private String telephone;
	@Column(name =  "email",length=4000)
	private String email;
	@Column(name =  "logo",length=4000)
	private String logo;
	public InformationShop() {
		super();
	}
	public InformationShop(Integer id, String telephone, String email, String logo) {
		super();
		this.id = id;
		this.telephone = telephone;
		this.email = email;
		this.logo = logo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}
