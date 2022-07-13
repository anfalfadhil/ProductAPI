package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognixia.jump.model.Product;
import com.cognixia.jump.model.User.Role;

@Entity
public class User implements Serializable {


	public enum Role {
		ROLE_USER, ROLE_ADMIN  
	}
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
//	@Column(nullable = false)
	private String username;
	
	
	/*
	 * It allows numeric values from 0 to 9.
	   Both uppercase and lowercase letters from a to z are allowed.
	   Allowed are underscore “_”, hyphen “-“, and dot “.”
	   Dot isn't allowed at the start and end of the local part.
	   Consecutive dots aren't allowed.
	   For the local part, a maximum of 64 characters are allowed.
	 */
	
	@Pattern(regexp =  "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "The pin must be a 4 digit number")
	
	//@Column(nullable = false, unique = true)
	private String email;
	
	
//	@Column(nullable = false)
	private String password;
	
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	
	@Column(nullable = true)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Product> my_list;

	@Column(columnDefinition = "boolean default true")
	private boolean enabled; 
	

	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JoinColumn(name = "cart", nullable = false)
    private Cart cart;
	
	
	public User () {
		
	}
	
	
	

	public User(Integer id, String username,
			@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "The pin must be a 4 digit number") String email,
			String password, Role role, List<Product> my_list, boolean enabled, Cart cart) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.my_list = my_list;
		this.enabled = enabled;
		this.cart = cart;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public Role getRole() {
		return role;
	}




	public void setRole(Role role) {
		this.role = role;
	}




	public List<Product> getMy_list() {
		return my_list;
	}




	public void setMy_list(List<Product> my_list) {
		this.my_list = my_list;
	}




	public boolean isEnabled() {
		return enabled;
	}




	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}




	public Cart getCart() {
		return cart;
	}




	public void setCart(Cart cart) {
		this.cart = cart;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}








	
	
	
	
}
