package com.cognixia.jump.model;

import java.util.List;
import com.cognixia.jump.model.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = true, length = 64)
	private String image;
	
	
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id" )
	private User user;
	
	
//	@ManyToOne()
//	@JoinColumn( name = "orders", referencedColumnName = "id" )
//	private List<Orders> orders;
	
	public Product() {
		
	}

	public Product(Integer id, String name, double price, String image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		//this.qty = qty;
		this.image = image;
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


	
//	public List<Orders> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Orders> orders) {
//		this.orders = orders;
//	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image name= " + image + "]";
	}


	
	
	
}
