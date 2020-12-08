package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ProductEntity;
import com.example.demo.pojo.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product")
@RefreshScope
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@Value("${welcome.message}")
	private String message;
	
	@RequestMapping("/")
	public String welcome() {
		return message;
	}
	
	@GetMapping("/all")
	public List<ProductEntity> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/add")
	public List<ProductEntity> addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@GetMapping("/get/code/{productcode}")
	public ProductEntity getProductStatusByCode(@PathVariable String productcode) {
		return productService.getProductStatusByCode(productcode);
	}

}
