package com.example.WebProject.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hoadonnhap")
public class HoaDonNhap implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	@ManyToOne
	@JoinColumn(name = "idpr")
	private Products product;
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name = "numpr", nullable = false)
	private int numpr;
	@Column(name = "nummoney", nullable = false)
	private int numMoney;
	
	
	public HoaDonNhap() {
		super();
	}


	public HoaDonNhap(Products product, Date date, int numpr, int numMoney) {
		super();
	
		this.product = product;
		this.date = date;
		this.numpr = numpr;
		this.numMoney = numMoney;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Products getProduct() {
		return product;
	}


	public void setProduct(Products product) {
		this.product = product;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getNumpr() {
		return numpr;
	}


	public void setNumpr(int numpr) {
		this.numpr = numpr;
	}


	public int getNumMoney() {
		return numMoney;
	}


	public void setNumMoney(int numMoney) {
		this.numMoney = numMoney;
	}

	

}
