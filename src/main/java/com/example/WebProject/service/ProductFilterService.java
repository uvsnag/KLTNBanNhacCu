package com.example.WebProject.service;

import java.util.List;

import com.example.WebProject.entity.Producer;
import com.example.WebProject.model.ProductInfo;

public interface ProductFilterService {

	List<ProductInfo> filterCategory(int category2, List<ProductInfo> listproduct) ;
	List<ProductInfo> filterProducer(int producer, List<ProductInfo> listproduct);
	List<Producer> list5Producer(int cid);
	List<ProductInfo> filterPrice(int price1,int price2, List<ProductInfo> listproduct);
	List<ProductInfo> filterPrice(int price1, List<ProductInfo> listproduct);
	
	
}
