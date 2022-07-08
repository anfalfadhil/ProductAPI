package com.cognixia.jump.controller;

import java.util.List;
import com.cognixia.jump.GlobalExceptionHandler.NoAdminsException;
import com.cognixia.jump.GlobalExceptionHandler.UserNotFoundException;

import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api")
@Tag(name = "User", description = "the API for managing users")
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
	

	
	@GetMapping("/all/admins")
	public List<User> getAllAdmins () throws NoAdminsException {
		return repo.findAdmins();
		
	}
	
	@GetMapping("/user/{id}")
	public Optional<User> getUser (@PathVariable Integer id) throws UserNotFoundException {
		Optional<User> found =  repo.findById(id);
		if (!found.isPresent()) {
			throw new UserNotFoundException ("user with id = " + id + "was not found");
		}
		
		return Optional.of(found.get());
	}
	
	
	@DeleteMapping("/user/{id}")  
	public  ResponseEntity<Integer> deleteUser(@PathVariable Integer id)  {
		Optional<User> found =  repo.findById(id);
		
		if (!found.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
		repo.deleteById(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
	
	
	
	 @PatchMapping("/user/{id}")
	 public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) throws UserNotFoundException {
		 
		 User toUpdate = repo.findById(id)
				 		  .orElseThrow(() -> new UserNotFoundException("user with this id: " + id + "was not found"));
		 
		 toUpdate.setEmail(user.getEmail());
		 toUpdate.setPassword(user.getPassword());
		 toUpdate.setUsername(user.getUsername());
		 
		 final User updatedUser = repo.save(toUpdate);
		 
		 return ResponseEntity.ok(updatedUser);
		 
		 
	 }
	
	
	
}
