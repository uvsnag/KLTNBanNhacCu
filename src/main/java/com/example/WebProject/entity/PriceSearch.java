
package com.example.WebProject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "price_search")
public class PriceSearch implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	
	@Column( name = "cid", nullable = false)
	private int cid;
	@Column(name = "price1", nullable = false)
	private int price1;
	@Column(name = "price2", nullable = false)
	private int price2;
	@Column(name = "price3", nullable = false)
	private int price3;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
	public int getPrice3() {
		return price3;
	}
	public void setPrice3(int price3) {
		this.price3 = price3;
	}
	
	
	
	
	
}
