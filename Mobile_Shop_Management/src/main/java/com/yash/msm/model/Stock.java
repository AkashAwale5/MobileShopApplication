package com.yash.msm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Stock //see all stock details;
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int itemId;
	@NotBlank(message = "Name is mandatory")
	String itemName;
	@NotBlank(message = "Company is mandatory")
	String company;
	//@Min(value = 1, message = "Quantity should be  minimum 1!!")
	
	int quantity;
	@Min(value = 10, message = "price should be minimum 10!!")
	double price;     //purchase price
	@Min(value = 10, message = "price should be minimum 10!!")
	double salePrice; //sale price
	double totalPrice;//total purchase price
	double totalSalePrice;//total sale price
	
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalSalePrice() {
		return totalSalePrice;
	}
	public void setTotalSalePrice(double totalSalePrice) {
		this.totalSalePrice = totalSalePrice;
	}
	
	
}
