package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.InventoryEntity;
import com.example.demo.pojo.Inventory;
import com.example.demo.service.InventoryService;

@RestController
public class InventoryRestController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping("/")
	public String welcome() {
		return "Hello from inventory microservice ver 1.0!!!!";
	}
	
	@GetMapping("/all")
	public List<InventoryEntity> getAllInventory() {
		return inventoryService.getAllInventory();
	}
	
	@PostMapping("/add")
	public List<InventoryEntity> addInventory(@RequestBody Inventory inventory) {
		return inventoryService.addInventory(inventory);
	}
	
	@GetMapping("/get/code/{productcode}")
	public InventoryEntity getInventoryStatusByCode(@PathVariable String productcode) {
		return inventoryService.getInventoryStatusByCode(productcode);
	}

}
