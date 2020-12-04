package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.ProductEntity;
import com.example.demo.pojo.InventoryResponse;
import com.example.demo.pojo.Product;
import com.example.demo.repository.ProductRepository;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public List<ProductEntity> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	public  List<ProductEntity> addProduct(Product product) {
		// TODO Auto-generated method stub
		ProductEntity pe = new ProductEntity();
		pe.setProductName(product.getProductName());
		pe.setProductPrice(product.getProductPrice());
		pe.setProductCode(product.getProductCode());
		pe.setInventoryStatus(product.isInventoryStatus());
		productRepository.save(pe);
		return productRepository.findAll();
	
	}

	public ProductEntity getProductStatusByCode(String productcode) {
		// TODO Auto-generated method stub
		
		String url = "http://localhost:8882/get/code/" + productcode;
		//Rest Template - Spring (Web Dependency) It is of sync(blocking) nature.
		//Web Client - Reactive Spring (Web Flux Dependency) It is of async(non-blocking) nature.
		//Http Client - Java 11 (sync (blocking) and async(non-blocking))
		ResponseEntity<InventoryResponse> response=   restTemplate.getForEntity(url, InventoryResponse.class);
		System.out.println(response.getStatusCodeValue());
		System.out.println(response.getBody().toString());
		if(response.getStatusCode() == HttpStatus.OK) {
			if(response.getBody().getProductQuantity()>0) {
				ProductEntity pe = productRepository.findByProductCode(productcode);
				pe.setInventoryStatus(true);
				productRepository.saveAndFlush(pe);
				
			}
	
		}
		
		
		return productRepository.findByProductCode(productcode);
	}

}
