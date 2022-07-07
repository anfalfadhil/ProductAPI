package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository repo;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = user;
		
		newUser.setId(-1);
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		
		User created = repo.save(newUser);
		return ResponseEntity.status(201).body(created);
	}
	
	
	@GetMapping("/user")
	public List<User> getUsers() {
		return repo.findAll();
	}
	
//	@DeleteMapping("/user/{id}")  
//	public Optional<User> deleteUser(@PathVariable Integer id)  { 
//		
//	
//	
//
//}
	
}
