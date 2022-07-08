package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.GlobalExceptionHandler.UserNotFoundException;
import com.cognixia.jump.model.Product;
import com.cognixia.jump.model.User;
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
	
	@GetMapping("/product/{id}")
	public Optional<Product> getAllProducts(@PathVariable Integer id) throws UserNotFoundException {
		Optional<Product> found = repo.findById(id);
		
		if (!found.isPresent()) {
			throw new UserNotFoundException("product with id: " + id + "was not found");
		}
		
		return Optional.of(found.get());
		
	}
	
	
	@DeleteMapping("/product/{id}")  
	public  ResponseEntity<Integer> deleteProduct(@PathVariable Integer id)  {
		Optional<Product> found =  repo.findById(id);
		
		if (!found.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
		repo.deleteById(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
	
	
	@PatchMapping("/product/{id}")
	 public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) throws UserNotFoundException {
		 
		 Product toUpdate = repo.findById(id)
				 		  .orElseThrow(() -> new UserNotFoundException("product with this id: " + id + "was not found"));
		 
		 toUpdate.setName(product.getName());
		 toUpdate.setPrice(product.getPrice());
		 toUpdate.setImage(product.getImage());
		 
		 final Product updatedProduct = repo.save(toUpdate);
		 
		 return ResponseEntity.ok(updatedProduct);
		 
		 
	 }
	
	
	
	
}












