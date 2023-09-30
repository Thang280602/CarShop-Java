package com.otothang.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "models")
	private String models;
	@Column(name = "productAddress")
	private String productAddress;
	@Column(name = "productName")
	private String productName;
	@Column(name = "price")
	private Double price;
	@Column(name = "image")
	private String image;
	
	@Column(name = "description",length=4000)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "categoryId",referencedColumnName = "id")
	private Category category;
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Set<ImageProduct> imageProduct;

	public Product() {
		super();
	}

	

	public Product(Integer id, String models, String productAddress, String productName, Double price, String image,
			String description, Category category, Set<ImageProduct> imageProduct) {
		super();
		this.id = id;
		this.models = models;
		this.productAddress = productAddress;
		this.productName = productName;
		this.price = price;
		this.image = image;
		this.description = description;
		this.category = category;
		this.imageProduct = imageProduct;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public String getProductAddress() {
		return productAddress;
	}

	public void setProductAddress(String productAddress) {
		this.productAddress = productAddress;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	public Set<ImageProduct> getImageProduct() {
		return imageProduct;
	}



	public void setImageProduct(Set<ImageProduct> imageProduct) {
		this.imageProduct = imageProduct;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", models=" + models + ", productAddress=" + productAddress + ", productName="
				+ productName + ", price=" + price + ", image=" + image + ", description=" + description + ", category="
				+ category + ", imageProduct=" + imageProduct + "]";
	}
	
}
