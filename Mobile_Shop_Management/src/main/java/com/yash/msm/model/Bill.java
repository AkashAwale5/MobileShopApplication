package com.yash.msm.model;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Bill 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int bilNo;
	LocalDate billDate;
	@NotBlank(message = "Customername is mandatory")
	String custName;
	String custPhone;
	//@NotBlank(message = "Address is mandatory")
	String custAddress;
	//@NotBlank(message = "salename is mandatory")
	String saleName;
	//@NotBlank(message = "itemName is mandatory")
	String itemName;
	int quantity;
	double price;
	double finalPrice;    //final price--mrp 
	double salePrice;//MRP
	double totalValue;

	public LocalDate getBillDate() {
		return billDate;
	}
	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

	
	public int getBilNo() {
		return bilNo;
	}
	public void setBilNo(int bilNo) {
		this.bilNo = bilNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	
}
