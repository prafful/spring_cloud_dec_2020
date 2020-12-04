package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.InventoryDao;
import com.example.demo.entity.InventoryEntity;
import com.example.demo.pojo.Inventory;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryDao inventoryDao;

	public  List<InventoryEntity> getAllInventory() {
		// TODO Auto-generated method stub
		return inventoryDao.getAllInventory();
	}

	public  List<InventoryEntity> addInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		return  inventoryDao.addInventory(inventory);
	}

	public InventoryEntity getInventoryStatusByCode(String productcode) {
		// TODO Auto-generated method stub
		return inventoryDao.getInventoryStatusByCode(productcode);
	}

}
