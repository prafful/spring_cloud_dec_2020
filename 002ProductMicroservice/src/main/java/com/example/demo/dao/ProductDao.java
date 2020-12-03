package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProductEntity;
import com.example.demo.pojo.Product;
import com.example.demo.repository.ProductRepository;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepository productRepository;

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

}
