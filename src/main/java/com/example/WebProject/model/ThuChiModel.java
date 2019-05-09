package com.example.WebProject.model;

import java.sql.Date;

public class ThuChiModel  {

	private Date  dateStart=null;
	private Date  dateEnd=null;
	private String  numBill="";
	private String  numMoneySell="";
	private String  numMoneyBuy="";
	private String revenue; 
	
	
	public ThuChiModel() {
		super();
	}
	 


	public String getRevenue() {
		return revenue;
	}
 


	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
 

 
	public String getNumBill() {
		return numBill;
	}



	public void setNumBill(String numBill) {
		this.numBill = numBill;
	}



	public String getNumMoneySell() {
		return numMoneySell;
	}



	public void setNumMoneySell(String numMoneySell) {
		this.numMoneySell = numMoneySell;
	}



	public String getNumMoneyBuy() {
		return numMoneyBuy;
	}



	public void setNumMoneyBuy(String numMoneyBuy) {
		this.numMoneyBuy = numMoneyBuy;
	}



	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
}
