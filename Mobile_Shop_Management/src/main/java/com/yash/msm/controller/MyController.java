package com.yash.msm.controller;



import java.time.LocalDate;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.yash.msm.model.Bill;
import com.yash.msm.model.Employee;
import com.yash.msm.model.Offers;
import com.yash.msm.model.Stock;
import com.yash.msm.model.User;
import com.yash.msm.model.sample;
import com.yash.msm.services.BillService;
import com.yash.msm.services.EmployeeService;
import com.yash.msm.services.OfferService;
import com.yash.msm.services.StockService;
import com.yash.msm.services.UserService;

@Controller
public class MyController
{
	int update_id;
	int before_quantity;
			
	@Autowired
	StockService objservice;
	
	@Autowired
	BillService objBill;
		
	@Autowired
	UserService objUservice;
	
	@Autowired
	OfferService objOffer;
	@Autowired
	EmployeeService eservice;
	@Autowired
	JdbcTemplate objjdbc;
//-----------------------------------Mapping Start--------------------------------------------------	
	//root 4038 3900 1013 1541 04/27
//	@RequestMapping("/")
//	public String home()
//	{		
//		return "index"; //home
//	}	
//-------------------------------------Security--------------------------------
	@RequestMapping("/")
	public String home1()
	{		
		return "home1"; //home1 trail
	}	
	@RequestMapping("/home")
	public String m2()
	{
		
		return "home";
	}
	@RequestMapping("/index")//admin
	public String m3()
	{
		return "index";
	}

	
//------------------------------------add stock ---products
//	@RequestMapping("/enterStock")
//	public String enterStockDetails()
//	{
//		return "stock";
//	}
//-------------------------------save stock--------------------------------------
//	@RequestMapping(value="/saveItem" ,method=RequestMethod.GET) 
//	public String saveItemdetails(@ModelAttribute Stock objstock)
//	{		
//		objstock.setTotalPrice(objstock.getPrice()*objstock.getQuantity());
//		objstock.setTotalSalePrice(objstock.getSalePrice()*objstock.getQuantity());
//		
//		int id=objservice.saveStock(objstock);
//		if(id<0)
//			return "stock";
//		else		
//			 return "redirect:/viewAllItem";
//	}
//-------------------------------------------validation changes-------------------------------------------
	@RequestMapping("/enterStock")
	public String enterStockDetails(Stock stock)
	{
		return "addStock";
	}
//----------------------save method with validation ------------------------------------------------------
	@RequestMapping(value="/saveItem" ,method=RequestMethod.GET) 
	public String saveItemdetails(@Valid  Stock objstock ,BindingResult bsr)
	{		
		objstock.setTotalPrice(objstock.getPrice()*objstock.getQuantity());
		objstock.setTotalSalePrice(objstock.getSalePrice()*objstock.getQuantity());		
		
		if(bsr.hasErrors())
			return "addStock";
		else	
		{
			 objservice.saveStock(objstock);
			 return "redirect:/viewAllItem";
		}
	}
//--------------------------------view All products------------------------------
	@RequestMapping("/viewAllItem")
	public ModelAndView viewAllItems( )
	{		
		ModelAndView objmv = new ModelAndView();
		objmv.setViewName("itemlist");
		objmv.addObject("ilist",objservice.getAllitems());
		return objmv;
	}
//---------------------------sale item -----------------------------------------
	@GetMapping("/saleItem/{id}")
	public String saleItem(@PathVariable(value="id") int id, Model m)
	{
		update_id=id;//used for update		
		Stock objStock=objservice.getItemById(id);
		m.addAttribute("stock", objStock);		
		return "Bill";
	}
//-------------------------Bill Modeule-------------------------------------ok work
	@PostMapping("/generateBill")
	public String generateBill(@ModelAttribute Bill objbill,Model m)
	{	
//-----------updating quantity----------------	
		Stock obj1= objservice.getItemById(update_id);
		obj1.setQuantity(obj1.getQuantity()-objbill.getQuantity());
		obj1.setTotalPrice(obj1.getPrice()*obj1.getQuantity());
		obj1.setTotalSalePrice(obj1.getSalePrice()*obj1.getQuantity());	
		objservice.saveStock(obj1);
			
//----save bill data to db----------------------------
		objbill.setBillDate(LocalDate.now());
		objbill.setTotalValue(objbill.getFinalPrice() * objbill.getQuantity());		
		objBill.generateBill(objbill);	
//------------------------------showing bill----------------------
		int billId= objbill.getBilNo();		
		Bill obj2= objBill.getBillByNo(billId);
		m.addAttribute("bill",obj2);		
		return "showBill";
	}
//---------------------------------with validation-------------------------------------------
//	@PostMapping("/generateBill")
//	public String generateBill(@Valid @ModelAttribute Bill objbill,Model m,Errors errors)
//	{	
////-----------updating quantity----------------	
//		Stock obj1= objservice.getItemById(update_id);
//		obj1.setQuantity(obj1.getQuantity()-objbill.getQuantity());
//		obj1.setTotalPrice(obj1.getPrice()*obj1.getQuantity());
//		obj1.setTotalSalePrice(obj1.getSalePrice()*obj1.getQuantity());
//		objservice.saveStock(obj1);
////----save bill data to db----------------------------
//		objbill.setBillDate(LocalDate.now());
//		objbill.setTotalValue(objbill.getFinalPrice() * objbill.getQuantity());
//		
//		if (null != errors && errors.getErrorCount() > 0)
//		{
//			 return "Bill";
//		} 
//	   else 
//		{
//		   objBill.generateBill(objbill);	
//		}
//------------------------------showing bill----------------------
//		int billId= objbill.getBilNo();		
//		Bill obj2= objBill.getBillByNo(billId);
//		m.addAttribute("bill",obj2);		
//		return "showBill";
//			
//	}
	
//-----------------------show transactions-----------------------------------------------
	@RequestMapping("/showtransactions")
	public ModelAndView showTransaction()
	{
		ModelAndView objmv = new ModelAndView();
		objmv.setViewName("show_transactions");
		objmv.addObject("transaction", objBill.get_Transaction());
		return objmv;
	}
	
	@GetMapping("/deleteBill/{id}")
	public String deleteBill(@PathVariable(value="id") int id )
	{
		objBill.deleteBillById(id);
		return "redirect:/showtransactions";
	}
//-----------------update---------------------------------------
	@GetMapping("/updateBill/{id}")
	public String updateBill(@PathVariable(value="id") int id,Model m)
	{
		Bill obj1=objBill.getBillDetails(id);		
		m.addAttribute("updatebill", obj1);
		return "update_bill";
	}
	@PostMapping("/updateBill")
	public String updateBillDetails(@ModelAttribute("updatebill") Bill obj1)
	{
		objBill.generateBill(obj1);
		 return "redirect:/showtransactions";
	}	
//---------------------------------Update  Stock Quantity ok-------------------------------
	@GetMapping("/updateItem/{id}")
	public String updateItem(@PathVariable(value="id") int id, Model m)
	{	
		Stock objStock=objservice.getItemById(id);
		before_quantity=objStock.getQuantity();	
		m.addAttribute("stock", objStock);
		return "updateItem";	
	}
//	@PostMapping("/updateStock")
//    public String addItem(@ModelAttribute("stock") Stock objStock)
// 	{     
//		int quan=objStock.getQuantity();
//		int totalquan=quan + before_quantity;
//				
//		objStock.setQuantity(totalquan);
//		objStock.setTotalSalePrice(objStock.getQuantity()*objStock.getSalePrice());
//		objStock.setTotalPrice(objStock.getQuantity()*objStock.getPrice());
//	
//		 objservice.saveStock(objStock);
//		 return "redirect:/viewAllItem";
//    }
//--------------------------------------with validation-----------------
	@PostMapping("/updateStock")
    public String addItem(@Valid @ModelAttribute("stock") Stock objStock,Errors errors)
 	{     
		int quan=objStock.getQuantity();
		int totalquan=quan + before_quantity;
				
		objStock.setQuantity(totalquan);
		objStock.setTotalSalePrice(objStock.getQuantity()*objStock.getSalePrice());
		objStock.setTotalPrice(objStock.getQuantity()*objStock.getPrice());
	 
	   if (null != errors && errors.getErrorCount() > 0)
		{
			 return "updateItem";
		} 
	   else 
		{
				objservice.saveStock(objStock);
				 return "redirect:/viewAllItem";	
		}

		 
    }
//-----------------------------showing present stcok ------------------------------
	@RequestMapping("/showStock")
	public String showStock()
	{		
		return "showStock";
	}
	@RequestMapping("/purchase")
	public ModelAndView puchaseStock(Model m)
	{
		String sql="select SUM(total_price) from stock";		
		double total=objjdbc.queryForObject(sql, Integer.class);
		m.addAttribute("totalprice", total);
		
		ModelAndView objmv = new ModelAndView();
		objmv.setViewName("purchaseTotal");
		objmv.addObject("ilist",objservice.getAllitems());
		
		return objmv;		
	}	
	@RequestMapping("/saleprice")	
	public ModelAndView salesStock(Model m)
	{
		String sql="select SUM(total_sale_price) from stock";		
		double total=objjdbc.queryForObject(sql, Integer.class);
		m.addAttribute("totalprice", total);
//------------------------show all-------------------------------------------------------------------------
		ModelAndView objmv = new ModelAndView();
		objmv.setViewName("saleprice");
		objmv.addObject("ilist",objservice.getAllitems());		
		return objmv;
	}
//------------------------------User Module----------------------addUser--------------
	@RequestMapping("/addUser")
	public String newUser()
	{
		return "addUser";
	}
	@RequestMapping(value="/saveUser" ,method=RequestMethod.GET) //mapping error 
	public String adduser(@ModelAttribute User objuser)
	{
		objuser.setActive(false);
		//System.out.println("im in save user");
		objUservice.saveUser(objuser);
		return "redirect:/showUser";
	}
	@RequestMapping("/showUser")
	public ModelAndView viewAllUser()
	{
		ModelAndView objmv= new ModelAndView();
		objmv.setViewName("userlist");
		objmv.addObject("ulist", objUservice.showAllUser());
		return objmv;		
	} 
//------------------------------saveEmployee-----------------------------------------------
	@RequestMapping("/employee")
	public String employee(Employee employee)
	{
		return"addEmployee";
	}
//	@RequestMapping(value="/saveEmployee" ,method=RequestMethod.GET)
//	public String addEmployee(@ModelAttribute Employee objemployee )
//	{
//		eservice.addEmployee(objemployee);
//		return "redirect:/showEmployee";
//	}
//--------------------------------validation--------------------------------------------------------------------------
	@RequestMapping(value="/saveEmployee" ,method=RequestMethod.GET)
	public String addEmployee(@Valid Employee employee ,BindingResult bsr)
	{
		if(bsr.hasErrors())
			return "addEmployee";
		else
		{
		eservice.addEmployee(employee);
		return "redirect:/showEmployee";
		}
	}
//--------------------------------------------------------------------------
	@RequestMapping("/showEmployee")
	public ModelAndView showEmployee()
	{
		ModelAndView objview= new ModelAndView();
		objview.setViewName("employeelist");
		objview.addObject("elist", eservice.allEmployee());
		return objview;
	}
//--------------------sales report monthly--------------------------------
	
	@RequestMapping("/salereport")
	public String salereport()
	{
		return"salereport";
	}
	@RequestMapping("/showreport")	
	public ModelAndView showReport(Model m,sample obj3)
	{	
		String startdate=obj3.getStartdate();
		String enddate=obj3.getEnddate();
		
		System.out.println("start date"+startdate);
		System.out.println("start date"+enddate);
				
		String sql="select SUM(total_value) from bill  where bill_date between '"+startdate+"' AND '"+enddate+"'";		
		double total=objjdbc.queryForObject(sql, Integer.class);
		
		String sql1="select SUM(price) from bill  where bill_date between '"+startdate+"' AND '"+enddate+"'";		
		double purchaseprice=objjdbc.queryForObject(sql1, Integer.class);
		String sql2="select SUM(final_price) from bill  where bill_date between '"+startdate+"' AND '"+enddate+"'";		
		double finalprice=objjdbc.queryForObject(sql2, Integer.class);

		double profit=0;
		double loss=0;
		if(finalprice<purchaseprice)
			loss=finalprice-purchaseprice;
		else
			profit=finalprice-purchaseprice;		
		
		m.addAttribute("totalprice", total);
		m.addAttribute("startdate", startdate);
		m.addAttribute("enddate",enddate);
		m.addAttribute("profit", profit);
		m.addAttribute("loss", loss);
		
		ModelAndView objmv= new ModelAndView();
		objmv.setViewName("showReport");
		//objmv.setViewName("saleReport");
		List<Bill> blist=objBill.getAllTrans(startdate,enddate);
		objmv.addObject("blist", blist);
		return objmv;		
	}
//----------------------------show all Bills---------------------------------
	@RequestMapping("/showallbills")
	public ModelAndView showAllBills()
	{
		ModelAndView objmv= new ModelAndView();
		objmv.setViewName("allBills");
		objmv.addObject("allbill", objBill.getAllBills());
		return objmv;
		
	}
	@GetMapping("/viewBill/{id}")
	public String viewAllBills(@PathVariable(value="id") int id ,Model m)
	{
		
		m.addAttribute("bills", objBill.showbillbyId(id));
		return "showbiilbyId";
	}
//-------------------------------------Offers----------------------------------------------------
	@RequestMapping("/enterOffer")
	public String enterOfferDetails(Offers offers)
	{
		return "addOffers";
	}
	@RequestMapping(value="/saveOffer" ,method=RequestMethod.GET) 
	public String saveOffertails(@ModelAttribute  Offers offers )
	{				
			objOffer.saveOffer(offers);
			return "redirect:/viewAllOffers";
	}
	@RequestMapping("/viewAllOffers")
	public ModelAndView viewOffers()
	{
		ModelAndView objmv = new ModelAndView();
		objmv.setViewName("alloffers");
		objmv.addObject("alloffer", objOffer.getAllOffers());
		return objmv;
	}
	@GetMapping("/deleteoffer/{id}")
	public String deleteOffer(@PathVariable(value="id") int id )
	{
		objOffer.deleteOfferById(id);
		return "redirect:/viewAllOffers";
	}
	@GetMapping("/updateoffer/{id}")
	public String updateoffer(@PathVariable(value="id") int id,Model m)
	{
		Offers obj1=objOffer.getofferById(id);	
		m.addAttribute("offer", obj1);
		return "update_Offer";
	}
	@PostMapping("/updatOffer")
	public String updateOfferDetails(@ModelAttribute("offer") Offers obj1)
	{
		objOffer.saveOffer(obj1);
		return "redirect:/viewAllOffers";
	}
	//---------------------------------------------Trail-----------------------------
	@RequestMapping("/viewAllOffersUser")
	public ModelAndView viewOffersUser()
	{
		ModelAndView objmv = new ModelAndView();
		objmv.setViewName("home");
		objmv.addObject("all", objOffer.getAllOffers());
		return objmv;
	}
}
