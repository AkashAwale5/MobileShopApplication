package com.yash.msm.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



public class sample 
{
	
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	    private String startdate;
	//@DateTimeFormat(pattern = "yyyy.MM.dd")
	
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	 private String enddate;
	
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
}
