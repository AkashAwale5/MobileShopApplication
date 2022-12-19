package com.yash.msm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.msm.crudrepo.MobileRepo;
import com.yash.msm.model.Bill;
import com.yash.msm.model.Stock;

@Service
public class StockService 
{
	@Autowired
	MobileRepo objRepo;

	public int saveStock(Stock objstock) 
	{
		objstock=objRepo.save(objstock);	
		int id=objstock.getItemId();
	//	System.out.println(objstock.getTotalPrice());
		return id;
	}

	public List<Stock> getAllitems()
	{
		List<Stock> itemList = new ArrayList<Stock>();		
		objRepo.findAll().forEach(itemList::add);
		return itemList;
	}
//---------------------------------updating sale-------------------------------------
	public Stock getItemById(int id)
	{
		Optional<Stock> optional=objRepo.findById(id);
		Stock objstock=optional.get();
		return objstock;
	}

	
	

	}
