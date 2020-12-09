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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

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
	
	@HystrixCommand(fallbackMethod = "getProductStatusByCodeFailed",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
			})

	public ProductEntity getProductStatusByCode(String productcode) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "http://localhost:8888/inventory/get/code/" + productcode;
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
	
	

	public ProductEntity getProductStatusByCodeFailed(String productCode) {
		System.out.println("##############################################################################################");
		System.out.println("#############################################Failed###########################################");
		System.out.println("##############################################################################################");
		ProductEntity pe = productRepository.findByProductCode(productCode);
		return pe;
		
	}
	
	
}
