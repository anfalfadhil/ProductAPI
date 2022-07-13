package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = true)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cart")
	private List<Product> product;

	public Cart() {

	}

	public Cart(Integer id, User user, List<Product> product) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
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

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	



}
