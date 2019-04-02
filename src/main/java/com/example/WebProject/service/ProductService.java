
package com.example.WebProject.service;
import java.util.List;

import com.example.WebProject.entity.Products;
import com.example.WebProject.model.CommentRateInfo;
import com.example.WebProject.model.ProductInfo;


public interface ProductService {

	 Iterable<Products> findAll(); 

    List<ProductInfo> search(String q);

	 Products findOne(int id); 

	 void save(Products contact); 

    void delete(int id);
     int count();
    List<Products> findProductBycategory(int cid);
   //
    List<CommentRateInfo> Get10Comment(int id);
     ProductInfo findProductInfo(int id);
     ProductInfo findProductInfoSave(int id);
     List<ProductInfo> findAllInfoProduct(int cid);
     List<ProductInfo> listProductInfo(int cid);
     void save(ProductInfo productInfo);
     void SaveCreate(ProductInfo productInfo);
}
