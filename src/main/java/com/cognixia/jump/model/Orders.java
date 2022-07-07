package com.cognixia.jump.model;

import java.util.List;
import com.cognixia.jump.model.Product;
import com.cognixia.jump.model.User;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Orders {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
//	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	private List<User> user;
	
//	@ManyToOne()
//	@JoinColumn( name = "product", referencedColumnName = "id" )
//	private List<Product> products;

	public Orders() {
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public List<User> getUser() {
//		return user;
//	}
//
//	public void setUser(List<User> user) {
//		this.user = user;
//	}

//	public List<Product> getProduct() {
//		return products;
//	}
//
//	public void setProduct(List<Product> product) {
//		this.products = product;
//	}
//	
	
	
	
	
	
	

}
