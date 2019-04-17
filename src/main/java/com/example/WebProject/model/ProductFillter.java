
package com.example.WebProject.model;

public class ProductFillter {

	private int category;
	private int category2;
	
	private int producer;
	private int price1;
	private int price2;
	//ké giá trong csdl
	private String priceView1;
	private String priceView2;
	private String priceView3;
	
	
	public int getCategory2() {
		return category2;
	}
	public void setCategory2(int category2) {
		this.category2 = category2;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getProducer() {
		return producer;
	}
	public ProductFillter(int category2, int producer, int price1, int price2) {
		super();
		
		this.category2 = category2;
		this.producer = producer;
		this.price1 = price1;
		this.price2 = price2;
	}
	public ProductFillter() {
		
	}
	public void setProducer(int producer) {
		this.producer = producer;
	}
	public int getPrice1() {
		return price1;
	}
	public void setPrice1(int price1) {
		this.price1 = price1;
	}
	public int getPrice2() {
		return price2;
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	public String getPriceView1() {
		return priceView1;
	}
	public void setPriceView1(String priceView1) {
		this.priceView1 = priceView1;
	}
	public String getPriceView2() {
		return priceView2;
	}
	public void setPriceView2(String priceView2) {
		this.priceView2 = priceView2;
	}
	public String getPriceView3() {
		return priceView3;
	}
	public void setPriceView3(String priceView3) {
		this.priceView3 = priceView3;
	}
	
	
	
}
