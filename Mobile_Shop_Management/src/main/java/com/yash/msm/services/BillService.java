package com.yash.msm.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.yash.msm.crudrepo.BillRepo;
import com.yash.msm.model.Bill;


@Service
public class BillService 
{
	@Autowired
	BillRepo objBrepo;
	
	@Autowired
	JdbcTemplate objjdbc;


	public void generateBill(Bill objbill) 
	{
		objBrepo.save(objbill);		
	}

	public Bill getBillByNo(int billId)
	{
		Optional<Bill> optional=objBrepo.findById(billId);
		Bill obj2=optional.get();
		return obj2;
	}

	public List<Bill> get_Transaction()
	{
		List<Bill> trlist = new ArrayList<>();
		objBrepo.findAll().forEach(trlist::add);
		return trlist;
	}

	public void deleteBillById(int id)
	{
		objBrepo.deleteById(id);
		
	}

	public Bill getBillDetails(int id) 
	{
		Optional<Bill> optional=objBrepo.findById(id);
		Bill obj =optional.get();
		return obj;
	}
	
//-------------------------------------------------------------------------------------------------------
	public List<Bill> getAllTrans(String startdate, String enddate) 
	{
		 return objjdbc.query("select * from bill where bill_date between '"+startdate+"' AND '"+enddate+"'",new RowMapper<Bill>()
		 {
			  public Bill mapRow(ResultSet rs, int row) throws SQLException
			  {
				  Bill obj = new Bill();	
				 
				  obj.setBilNo(rs.getInt("bil_no"));
				  
				//  obj.setBillDate(rs.getDate("bill_date"));
				  obj.setCustName(rs.getString("cust_name"));
				  obj.setPrice(rs.getDouble("price"));
				  obj.setItemName(rs.getString("item_name"));
				  obj.setFinalPrice(rs.getDouble("final_price"));				  
				  obj.setTotalValue(rs.getDouble("total_value"));
				  obj.setSaleName(rs.getString("sale_name"));
			  return obj;
			  }
		 });
	}

	public List<Bill> getAllBills()
	{
		List<Bill> billlist=new ArrayList<Bill>();
		objBrepo.findAll().forEach(billlist::add);
		return billlist;
	}

	public Bill showbillbyId(int id)
	{
		Optional<Bill> optional=objBrepo.findById(id);
		Bill obj2=optional.get();
		return obj2;
		
	}
}
