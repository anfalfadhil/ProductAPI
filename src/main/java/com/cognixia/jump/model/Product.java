package com.cognixia.jump.model;

import java.util.List;
import com.cognixia.jump.model.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private double price;
	
	private String description;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;

	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "cart", nullable = true)
	private Cart cart;

	public Product() {

	}








	





	public Product(Integer id, String name, double price, String description, String image, User user, Cart cart) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.user = user;
		this.cart = cart;
	}














	public String getImage() {
		return image;
	}














	public void setImage(String image) {
		this.image = image;
	}














	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
