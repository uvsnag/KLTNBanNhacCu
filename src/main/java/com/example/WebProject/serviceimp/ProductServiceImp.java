
package com.example.WebProject.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebProject.dao.ProductDao;
import com.example.WebProject.dao.ProductFilterDao;
import com.example.WebProject.entity.Producer;
import com.example.WebProject.entity.Products;
import com.example.WebProject.model.CommentRateInfo;
import com.example.WebProject.model.ProductInfo;
import com.example.WebProject.repository.ProductRepository;
import com.example.WebProject.service.ProductFilterService;
import com.example.WebProject.service.ProductService;

@Service
public class ProductServiceImp implements ProductService, ProductFilterService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductFilterDao ProductFilterDao;

	@Override
	public Iterable<Products> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<ProductInfo> search(String q) {
		return productDao.Search(q);
	}

	@Override
	public Products findOne(int id) {
		return productRepository.findOne(id);
	}

	@Override
	public void save(Products contact) {
		productRepository.save(contact);
	}

	@Override
	public void delete(int id) {
		productRepository.delete(id);
	}

	@Override
	public int count() {

		return (int) productRepository.count();

	}

	@Override
	public List<Products> findProductBycategory(int cid) {
		return productRepository.findProductssByCategory2id(cid);

	}

//
	//
	//
	@Override
	public List<CommentRateInfo> Get10Comment(int id) {

		return productDao.Get10Comment(id);
	}

	@Override
	public ProductInfo findProductInfo(int id) {

		return productDao.findProductInfo(id);
	}

	@Override
	public ProductInfo findProductInfoSave(int id) {

		return productDao.findProductInfoSave(id);
	}

	@Override
	public List<ProductInfo> findAllInfoProduct(int cid) {

		return productDao.findAllInfoProduct(cid);
	}

	@Override
	public List<ProductInfo> listProductInfo(int cid) {

		return productDao.listProductInfo(cid);
	}

	@Override
	public void save(ProductInfo productInfo) {
		productDao.save(productInfo);

	}

	@Override
	public void SaveCreate(ProductInfo productInfo) {

		productDao.SaveCreate(productInfo);
	}
//

	@Override
	public List<ProductInfo> filterCategory(int category2, List<ProductInfo> listproduct) {
		return ProductFilterDao.filterCategory(category2, listproduct);
	}

	@Override
	public List<ProductInfo> filterProducer(int producer, List<ProductInfo> listproduct) {
		return ProductFilterDao.filterProducer(producer, listproduct);
	}

	@Override
	public List<Producer> list5Producer(int cid) {
		return ProductFilterDao.list5Producer(cid);
	}

	@Override
	public List<ProductInfo> filterPrice(int price1, int price2, List<ProductInfo> listproduct) {
		return ProductFilterDao.filterPrice(price1, price2, listproduct);
	}

	@Override
	public List<ProductInfo> filterPrice(int price1, List<ProductInfo> listproduct) {
		return ProductFilterDao.filterPrice(price1, listproduct);
	}

}
