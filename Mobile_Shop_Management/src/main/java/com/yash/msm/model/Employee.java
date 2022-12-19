package com.yash.msm.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Employee 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@NotBlank(message = "*Name is mandatory")
	String name;
	@NotBlank(message = "*Address is mandatory")
	String address;
	@Size(min=10, max=10, message="Phone No can not less than or max than 10")
	String phoneno;
	@NotBlank(message = "*Role is mandatory")
	String role;	
	
	Date dob;
	Date joinDate;
	
	@Min(value = 3000, message = "Salary should be  minimum 3000!!")
	int salary;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}
