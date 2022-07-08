package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Product;
import com.cognixia.jump.repository.ProductRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api")
@RestController
@Tag(name = "product", description = "the API for managing products")
public class ProductController {
	
	@Autowired
	ProductRepository repo;
	
	@GetMapping("/product")
	public List<Product> getProducts() {
		return repo.findAll();
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		
		product.setId(-1);
		
		Product created = repo.save(product);
		
		return ResponseEntity.status(201).body(created);
	}
}












