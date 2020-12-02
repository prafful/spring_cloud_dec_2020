package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private Integer id;
	private String productName;
	private double productPrice;
	private boolean inventoryStatus;
	private String productCode;
	
}
