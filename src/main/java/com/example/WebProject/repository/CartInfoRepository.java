
package com.example.WebProject.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.WebProject.entity.CartInfo;
import com.example.WebProject.entity.Customer;

public interface CartInfoRepository extends CrudRepository<CartInfo, Integer> {

	public List<CartInfo> findCartInfosByIdcustomer(Customer q);
	public List<CartInfo> findByDateBetween(Date from, Date to);
}
