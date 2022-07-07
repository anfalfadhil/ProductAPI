package com.cognixia.jump.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;



@Service
public class UserDetailsServiceX implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if (userOpt.isEmpty()) {
			new UsernameNotFoundException("username could not be found");
		}
		return new MyUserDetails(userOpt.get());
	}

}
