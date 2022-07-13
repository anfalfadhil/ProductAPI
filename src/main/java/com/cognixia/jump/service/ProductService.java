package com.cognixia.jump.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cognixia.jump.model.Product;
import com.cognixia.jump.repository.ProductRepository;

@Service
public class ProductService {

	
	@Autowired
	private ProductRepository repo;
	
	public Product createProduct(MultipartFile file, String name, String description, int price) throws IOException {
		
		Product p = new Product();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if (fileName.contains("..")) {
			System.out.println("Not a valid file");
		}
		
		
		p.setDescription(description);
		p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		p.setName(name);
		p.setPrice(price);
		
		repo.save(p);
		return p;
	}
	
	
	
	
	
	
}
