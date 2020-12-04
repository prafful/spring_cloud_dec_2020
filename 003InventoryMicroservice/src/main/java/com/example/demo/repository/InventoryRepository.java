package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.InventoryEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer>{

	//named query or derived query
	 InventoryEntity  findByProductCode(String productcode);
	
}
