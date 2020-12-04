package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.InventoryEntity;
import com.example.demo.pojo.Inventory;
import com.example.demo.repository.InventoryRepository;

@Repository
public class InventoryDao {
	
	@Autowired
	private InventoryRepository inventoryRepository;

	public List<InventoryEntity> getAllInventory() {
		// TODO Auto-generated method stub
		return inventoryRepository.findAll();
	}

	public  List<InventoryEntity> addInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		InventoryEntity ie = new InventoryEntity();
		ie.setProductCode(inventory.getProductCode());
		ie.setProductQuantity(inventory.getProductQuantity());
		inventoryRepository.save(ie);
		return inventoryRepository.findAll();
	
	}

	public InventoryEntity getInventoryStatusByCode(String productcode) {
		// TODO Auto-generated method stub
		return inventoryRepository.findByProductCode(productcode);
		
	}

}
