package com.cognixia.jump.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Cart;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.CartRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Cart", description = "the API for managing carts")
public class CartController {

	@Autowired 
	CartRepository repo;
	
	
	@PostMapping("/cart")
	public ResponseEntity<Cart> createOrder(@Valid @RequestBody Cart cart) {
		Cart newCart = cart;
		
		newCart.setId(-1);
		
		Cart created = repo.save(newCart);
		return ResponseEntity.status(201).body(created);
	}
	
	
	
	
}
