
package com.example.WebProject.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebProject.entity.Producer;
import com.example.WebProject.model.ProductInfo;
import com.example.WebProject.repository.Category2Repository;
import com.example.WebProject.repository.ProducerRepository;
import com.example.WebProject.repository.ProductRepository;
@Transactional
@Repository
public class ProductFilterDao {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
    private Category2Repository category2Repository;
	 
	@Autowired
    private ProducerRepository producerRepository;
	
		
	//FILLTER
		//find with category
public List<ProductInfo> filterCategory(int category2, List<ProductInfo> listproduct) {
    	
        try {
        	List<ProductInfo> listproduct2=new ArrayList<ProductInfo>();
        	
        	for(int i=0; i<listproduct.size(); i++){
        		if(listproduct.get(i).getCategory2().equals(category2Repository.findOne(category2).getCategory())){
        			listproduct2.add(listproduct.get(i));
        		}
        	}
            return listproduct2;
        	} catch (NoResultException e) {
            return null;
        }
    }
public List<ProductInfo> filterProducer(int producer, List<ProductInfo> listproduct) {
	
    try {
    	List<ProductInfo> listproduct2=new ArrayList<ProductInfo>();
    	
    	for(int i=0; i<listproduct.size(); i++){
    		if(listproduct.get(i).getProducer().equals(producerRepository.findOne(producer).getName())){
    			listproduct2.add(listproduct.get(i));
    		}
    	}
        return listproduct2;
    	} catch (NoResultException e) {
        return null;
    }
}

//fill <=5 producer
	public List<Producer> list5Producer(int cid) {
	
	  try {
        	List<Producer> listproducer=new ArrayList<Producer>();
        	List<Producer> producer=(List<Producer>) producerRepository.findProducersByIdpr(cid);
       
        	
        	for(int i=0; i<5; i++){
        		if(i>=producer.size()){
        			 return listproducer;
        		 }
        		
        		listproducer.add(producer.get(i));
        		
        			}
            return listproducer;
        } catch (NoResultException e) {
            return null;
        }
    }
	
	public List<ProductInfo> filterPrice(int price1,int price2, List<ProductInfo> listproduct) {
		
	    try {
	    	List<ProductInfo> listproduct2=new ArrayList<ProductInfo>();
	    	int n;
	    	for(int i=0; i<listproduct.size(); i++){
	    		n=Integer.parseInt(productRepository.findOne(listproduct.get(i).getId()).getGiasaugiam());
	    		if(n>=price1&&n<=price2){
	    			listproduct2.add(listproduct.get(i));
	    		}
	    	}
	        return listproduct2;
	    	} catch (NoResultException e) {
	        return null;
	    }
	}
	public List<ProductInfo> filterPrice(int price1, List<ProductInfo> listproduct) {
		
	    try {
	    	List<ProductInfo> listproduct2=new ArrayList<ProductInfo>();
	    	int n;
	    	for(int i=0; i<listproduct.size(); i++){
	    		n=Integer.parseInt(productRepository.findOne(listproduct.get(i).getId()).getGiasaugiam());
	    		if(n>=price1){
	    			listproduct2.add(listproduct.get(i));
	    		}
	    	}
	        return listproduct2;
	    	} catch (NoResultException e) {
	        return null;
	    }
	}
	
}
