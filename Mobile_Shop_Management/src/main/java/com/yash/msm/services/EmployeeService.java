package com.yash.msm.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.msm.crudrepo.EmployeeRepo;
import com.yash.msm.model.Employee;

@Service
public class EmployeeService 
{
	@Autowired
	EmployeeRepo objERepo;
	
	
	public void addEmployee(Employee objE)
	{
		objERepo.save(objE);
	}
	
	public List<Employee> allEmployee()
	{
		List<Employee> elist= new ArrayList<Employee>();
		objERepo.findAll().forEach(elist::add);
		return elist;
	}
}
