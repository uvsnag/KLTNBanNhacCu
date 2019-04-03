
package com.example.WebProject.dao;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebProject.entity.CommentRateView;

import com.example.WebProject.entity.Products;
import com.example.WebProject.model.CommentRateInfo;
import com.example.WebProject.model.ProductInfo;
import com.example.WebProject.repository.Category2Repository;
import com.example.WebProject.repository.CategoryRepository;
import com.example.WebProject.repository.ColorRepository;
import com.example.WebProject.repository.ProducerRepository;
import com.example.WebProject.repository.ProductRepository;

@Transactional
@Repository
public class ProductDao {
	
	@Autowired
	 private ProductRepository productRepository;
	@Autowired
    private CategoryRepository categoryRepository;
	@Autowired
    private Category2Repository category2Repository;
	@Autowired
    private ColorRepository colorRepository;
	@Autowired
    private ProducerRepository producerRepository;


	public List<ProductInfo> Search(String q) {

		try {

			
			List<ProductInfo> listproduct = new ArrayList<ProductInfo>();
		
			ProductInfo gtinfo;

			for (Products gt: productRepository.findByNameContaining(q)) {// search products
				
				
					gtinfo = new ProductInfo(gt.getId(), gt.getName(), gt.getCategoryid().getCategory(),
							gt.getCategory2id().getCategory(), gt.getProducerid().getName(), gt.getColorid().getName(),
							(int) gt.getRate(), gt.getSoluong(), intien(gt.getGia()), gt.getSoluot(), gt.getGiamgia(),
							intien(gt.getGiasaugiam()));
					if (gt.getDatepr() != null) {
						gtinfo.setDatepr(gt.getDatepr().toString());
					}

					listproduct.add(gtinfo);
				

			}
			return listproduct;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/* ??????????????????????????????????????????????????????????????? */
	//
	// get 10 comment most recent to display on comment
	//
	//
	
	public List<CommentRateInfo> Get10Comment(int id) {//id: id of product

		List<CommentRateView> list = new ArrayList<CommentRateView>();
		List<CommentRateInfo> listInfo = new ArrayList<CommentRateInfo>();
		list.addAll(productRepository.findOne(id).getCommentrateview());
		 SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");  
		if (list.size() <= 0) {
			return null;
		}
		int i = 0;
		while (i < list.size()) {
			listInfo.add(new CommentRateInfo(list.get(i).getRate()/2, list.get(i).getComment(),
					formatter.format(list.get(i).getDate()).toString(), list.get(i).getCustommer().getName()));
			i++;
		}

		return listInfo;
	}

	// hien thi sp - in tien
	public ProductInfo findProductInfo(int id) {

		try {
			Products gt = productRepository.findOne(id);
			if (gt == null) {
				return null;
			}

			ProductInfo gtinfo = new ProductInfo(gt.getId(), gt.getName(), gt.getCategoryid().getCategory(),
					gt.getCategory2id().getCategory(), gt.getProducerid().getName(), gt.getColorid().getName(),
					(int) gt.getRate(), gt.getSoluong(), intien(gt.getGia()), gt.getSoluot(), gt.getGiamgia(),
					intien(gt.getGiasaugiam()), gt.getVisits());
			if (gt.getDatepr() != null) {
				gtinfo.setDatepr(gt.getDatepr().toString());
			}
			return gtinfo;
		} catch (NoResultException e) {
			return null;
		}
	}

	// hien thi sp - khong in tien
	public ProductInfo findProductInfoSave(int id) {

		try {
			Products gt = productRepository.findOne(id);
			if (gt == null) {
				return null;
			}
			ProductInfo gtinfo = new ProductInfo(gt.getId(), gt.getName(), gt.getCategoryid().getCategory(),
					gt.getCategory2id().getCategory(), gt.getProducerid().getName(), gt.getColorid().getName(),
					(int) gt.getRate(), gt.getSoluong(), gt.getGia(), gt.getSoluot(), gt.getGiamgia(),
					gt.getGiasaugiam());
			if (gt.getDatepr() != null) {
				gtinfo.setDatepr(gt.getDatepr().toString());
			}

			return gtinfo;
		} catch (NoResultException e) {
			return null;
		}
	}

	// find all
	public List<ProductInfo> findAllInfoProduct(int cid) {//cid: id of category2

		try {

			
			List<ProductInfo> listproduct = new ArrayList<ProductInfo>();
		
			ProductInfo gtinfo;

			for (Products gt: productRepository.findAll()) {
				
				if (gt.getCategoryid().getId() == cid) {
					gtinfo = new ProductInfo(gt.getId(), gt.getName(), gt.getCategoryid().getCategory(),
							gt.getCategory2id().getCategory(), gt.getProducerid().getName(), gt.getColorid().getName(),
							(int) gt.getRate(), gt.getSoluong(), intien(gt.getGia()), gt.getSoluot(), gt.getGiamgia(),
							intien(gt.getGiasaugiam()));
					if (gt.getDatepr() != null) {
						gtinfo.setDatepr(gt.getDatepr().toString());
					}

					listproduct.add(gtinfo);
				}

			}
			return listproduct;
		} catch (NoResultException e) {
			return null;
		}
	}

	// find for index.html
	public List<ProductInfo> listProductInfo(int cid) {

		try {
			List<ProductInfo> listproduct = new ArrayList<ProductInfo>();
			List<Products> product = (List<Products>) productRepository.findAll();

			Products gt = new Products();
			ProductInfo gtinfo;
			int i = product.size() - 1;

			while (i >= 0) {

				gt = product.get(i);
				if (gt.getCategoryid().getId() == cid) {

					gtinfo = new ProductInfo(gt.getId(), gt.getName(), gt.getCategoryid().getCategory(),
							gt.getCategory2id().getCategory(), gt.getProducerid().getName(), gt.getColorid().getName(),
							(int) gt.getRate(), gt.getSoluong(), intien(gt.getGia()), gt.getSoluot(), gt.getGiamgia(),
							intien(gt.getGiasaugiam()));
					if (gt.getDatepr() != null) {
						gtinfo.setDatepr(gt.getDatepr().toString());
					}

					listproduct.add(gtinfo);
					if (listproduct.size() == 4) {
						return listproduct;
					}
				}
				i--;
			}

			return listproduct;
		} catch (

		NoResultException e) {
			return null;
		}
	}

	public void save(ProductInfo productInfo) {
		// create objectProductsfrom productinfo
		final byte[] productImage = productRepository.findOne(productInfo.getId()).getImage();
		Products gt = new Products(productInfo.getId(), productInfo.getName(),
				categoryRepository.findByCategoryContaining(productInfo.getCategory()).get(0),
				category2Repository.findByCategoryContaining(productInfo.getCategory2()).get(0),
				producerRepository.findByNameContaining(productInfo.getProducer()).get(0),
				colorRepository.findByName(productInfo.getColor()).get(0), productInfo.getSoluong(),
				productInfo.getGia(), productInfo.getGiamgia(),
				GiaSauGiam(Integer.parseInt(productInfo.getGia()), productInfo.getGiamgia()));
		// set date
		if (productInfo.getDatepr().toString().trim().equals("") == false) {

			String date = productInfo.getDatepr().toString();
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date startDate;
			try {
				startDate = sdf.parse(date);
				java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
				gt.setDatepr(sqlDate);
				/* System.out.println("ok"); */

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else
			gt.setDatepr(null);
		// set image

		if (productInfo.getFileData().isEmpty() == false) {
			byte[] image = null;
			try {
				image = productInfo.getFileData().getBytes();
			} catch (IOException e) {
			}
			if (image != null && image.length > 0) {
				gt.setImage(image);
			}
		} else
			gt.setImage(productImage);

		productRepository.save(gt);

	}

	public void SaveCreate(ProductInfo productInfo) {
	
		// int entityProductsfrom productInfo-non gianiemyet, rate, status
		String s=productInfo.getColor();
		System.out.println(s);
		System.out.println(colorRepository.findByName(productInfo.getColor()).size());
		Products gt = new Products( productInfo.getName(),
				categoryRepository.findByCategoryContaining(productInfo.getCategory()).get(0),
				category2Repository.findByCategoryContaining(productInfo.getCategory2()).get(0),
				producerRepository.findByNameContaining(productInfo.getProducer()).get(0),
				colorRepository.findByName(productInfo.getColor()).get(0), 
				0, 0, productInfo.getSoluong(),
				productInfo.getGia(), productInfo.getGiamgia(),
				GiaSauGiam(Integer.parseInt(productInfo.getGia()), productInfo.getGiamgia()), 0);
		// set date
		if (productInfo.getDatepr().toString().trim().equals("") == false) {

			String date = productInfo.getDatepr().toString();
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date startDate;
			try {
				startDate = sdf.parse(date);
				java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
				gt.setDatepr(sqlDate);
				/* System.out.println("ok"); */

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else
			gt.setDatepr(null);
		// set image
		if (productInfo.getFileData() != null) {
			byte[] image = null;
			try {
				image = productInfo.getFileData().getBytes();
			} catch (IOException e) {
			}
			if (image != null && image.length > 0) {
				gt.setImage(image);
			}
		}

		productRepository.save(gt);


	}

	static public String GiaSauGiam(int gia, int giam) {
		return String.valueOf(gia - (gia * giam) / 100);

	}

	static public String intien(String sotien) {
		sotien = sotien.trim();
		int n = sotien.length() - 1;
		int s = 0;
		String tienphu = "";
		while (n >= 0) {
			if (s == 3) {
				s = 0;
				tienphu = tienphu + ",";
			}
			tienphu = tienphu + sotien.charAt(n);
			s++;
			n--;

		}
		sotien = "";
		for (int i = tienphu.length() - 1; i >= 0; i--) {
			sotien = sotien + tienphu.charAt(i);
		}
		if (sotien.charAt(0) == '-' && sotien.charAt(1) == ',') {
			String st = sotien;
			sotien = "-";
			for (int i = 2; i <= st.length() - 1; i++) {
				sotien = sotien + st.charAt(i);
			}
		}
		return sotien + " VND";
	}

}
