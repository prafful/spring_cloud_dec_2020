package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.ProductEntity;
import com.example.demo.pojo.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;

	public  List<ProductEntity> getAllProducts() {
		// TODO Auto-generated method stub
		return productDao.getAllProducts();
	}

	public  List<ProductEntity> addProduct(Product product) {
		// TODO Auto-generated method stub
		return  productDao.addProduct(product);
	}

}
