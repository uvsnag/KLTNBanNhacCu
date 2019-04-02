

package com.example.WebProject.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "phone", nullable = true)
	private String phone;
	@Column(name = "name", nullable = true)
	private String name;
	@Column(name = "address", nullable = true)
	private String address;
	@OneToMany(mappedBy = "idcustomer",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	private Collection<CartInfo> cartInfos;
	@Column(name = "email", nullable = true)
	private String email;
	
	
	public Customer() {
		super();
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	
}
