
package com.example.WebProject.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.WebProject.WebProjectApplication;
import com.example.WebProject.entity.PriceSearch;
import com.example.WebProject.model.ProductFillter;
import com.example.WebProject.model.ProductInfo;
import com.example.WebProject.service.Category2Service;
import com.example.WebProject.service.PriceSearchService;
import com.example.WebProject.service.ProducerService;
import com.example.WebProject.service.ProductFilterService;
import com.example.WebProject.service.ProductService;


@Controller
public class FilterUkuleleController {
	@Autowired
	private ProductFilterService productFilterService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	
	private ProducerService producerService;
	
	@Autowired
	private Category2Service category2Service;
	@Autowired
	private PriceSearchService priceSearchService;

	private void AddAtribute(Model model){
		ProductFillter prdFilter=new ProductFillter(-1, -1, -1, -1);
		PriceSearch priceSearch =priceSearchService.findOne(WebProjectApplication.filterUkulele);
		prdFilter.setPriceView1(priceSearch.getPrice1()%10000==0 ? Integer.toString(priceSearch.getPrice1()/1000) 
				: Integer.toString(priceSearch.getPrice1()/1000)+","+Integer.toString((priceSearch.getPrice1()%1000)/100));
		prdFilter.setPriceView2(priceSearch.getPrice2()%1000000==0 ? Integer.toString(priceSearch.getPrice2()/1000000) 
				: Integer.toString(priceSearch.getPrice2()/1000000)+","+Integer.toString((priceSearch.getPrice2()%1000000)/100000));
		prdFilter.setPriceView3(priceSearch.getPrice3()%1000000==0 ? Integer.toString(priceSearch.getPrice3()/1000000) 
				: Integer.toString(priceSearch.getPrice3()/1000000)+","+Integer.toString((priceSearch.getPrice3()%1000000)/100000));
		
		model.addAttribute("filter",prdFilter );
		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterUkulele));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterUkulele));
		model.addAttribute("prnumber", WebProjectApplication.productNumber);	
	}
	@GetMapping("/ukulelefilterCategory/{id}")
	public String adukuleleacousticCategory(Model model, @PathVariable int id) {
		
		model.addAttribute("infos", productFilterService.filterCategory(id, productService.findAllInfoProduct(WebProjectApplication.filterUkulele)));
		AddAtribute(model);
		model.addAttribute("tittle", "UKULELE >LOẠI");
		return "/ukulele";
	}
	@GetMapping("/ukulelefilterProducer/{id}")
	public String adukuleleacousticProducer(Model model, @PathVariable int id) {
		
		model.addAttribute("infos", productFilterService.filterProducer(id, productService.findAllInfoProduct(WebProjectApplication.filterUkulele)));
		AddAtribute(model);
		model.addAttribute("tittle", "UKULELE >HÃNG");
		return "/ukulele";
	}
	
	
	@GetMapping("/ukulelefilterPrice/{p1}")
	public String adukulele1pricer(Model model, @PathVariable int p1) {
		
		model.addAttribute("infos", ListPrice(p1, productService.findAllInfoProduct(WebProjectApplication.filterUkulele)));
		AddAtribute(model);
		model.addAttribute("tittle", "UKULELE");
		return "/ukulele";
	}
	@GetMapping("/ukulelefilter")
	public String filter(Model model, @Valid ProductFillter filter) {
		String s="UKULELE";
		List<ProductInfo> list=productService.findAllInfoProduct(WebProjectApplication.filterUkulele);
		if(filter==null)
		{
			AddAtribute(model);
			model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterUkulele));
			model.addAttribute("tittle", "UKULELE ");
			return "ukulele";
		}
		if(filter.getCategory2()>0){
			list=productFilterService.filterCategory(filter.getCategory2(), list);
			if(category2Service.findOne(filter.getCategory2()).getCategory()!=null)
			s=s+" > "+category2Service.findOne(filter.getCategory2()).getCategory();
		}
		
		if(filter.getProducer()>0){
			list=productFilterService.filterProducer(filter.getProducer(), list);
			if(producerService.findOne(filter.getProducer()).getName()!=null)
			s=s+" > "+producerService.findOne(filter.getProducer()).getName();
		}
		if(filter.getPrice1()>0&&filter.getPrice1()<=4){
			
			list=ListPrice(filter.getPrice1(), productService.findAllInfoProduct(WebProjectApplication.filterUkulele));
			s=s+" > Giá";
		}
		
		model.addAttribute("infos", list);
		AddAtribute(model);
		model.addAttribute("tittle", s);
		return "/ukulele";
	}
	List<ProductInfo> ListPrice(int p1, List<ProductInfo> listukuleleInfo  ){
		List<ProductInfo> ukuleleInfo=new ArrayList<ProductInfo>();
		PriceSearch priceSearch =priceSearchService.findOne(WebProjectApplication.filterUkulele);
		switch(p1){
		case 1: ukuleleInfo=productFilterService.filterPrice(1,priceSearch.getPrice1(), listukuleleInfo);
		break;
		case 2: ukuleleInfo=productFilterService.filterPrice(priceSearch.getPrice1(),priceSearch.getPrice2(), listukuleleInfo);
		break;
		case 3: ukuleleInfo=productFilterService.filterPrice(priceSearch.getPrice2(),priceSearch.getPrice3(), listukuleleInfo);
		break;
		case 4: ukuleleInfo=productFilterService.filterPrice(priceSearch.getPrice3(), listukuleleInfo);
		break;
		}
		return ukuleleInfo;
	}
	/*admin*/
	@GetMapping("/adukulelefilterCategory/{id}")
	public String FindCategory(Model model, @PathVariable int id) {
		
		model.addAttribute("infos", productFilterService.filterCategory(id, productService.findAllInfoProduct(WebProjectApplication.filterUkulele)));
		AddAtribute(model);
		model.addAttribute("tittle", "UKULELE >LOẠI");
		return "/admin/UkuleleAd";
	}
	@GetMapping("/adukulelefilterProducer/{id}")
	public String adukuleleProducer(Model model, @PathVariable int id) {
		
		model.addAttribute("infos",  productFilterService.filterProducer(id, productService.findAllInfoProduct(WebProjectApplication.filterUkulele)));
		AddAtribute(model);
		model.addAttribute("tittle", "UKULELE >HÃNG");
		return "/admin/UkuleleAd";
	}

	
	@GetMapping("/adukulelefilterPrice/{p1}")
	public String aadukulele1pricer(Model model, @PathVariable int p1) {
		
		
		
	
		
		model.addAttribute("infos",ListPrice(p1, productService.findAllInfoProduct(WebProjectApplication.filterUkulele)));
		AddAtribute(model);
		model.addAttribute("tittle", "UKULELE");
		return "/admin/UkuleleAd";
	}
	@GetMapping("/adukulelefilter")
	public String afilter(Model model, @Valid ProductFillter filter) {
		String s="UKULELE";
		List<ProductInfo> list=productService.findAllInfoProduct(WebProjectApplication.filterUkulele);
		if(filter==null)
		{
			AddAtribute(model);
			model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterUkulele));
		
			return "admin/UkuleleAd";
		}
		if(filter.getCategory2()>0){
			list=productFilterService.filterCategory(filter.getCategory2(), list);
			s=s+" > "+category2Service.findOne(filter.getCategory2()).getCategory();
		}
		
		if(filter.getProducer()>0){
			list=productFilterService.filterProducer(filter.getProducer(), list);
			s=s+" > "+producerService.findOne(filter.getProducer()).getName();
		}
		if(filter.getPrice1()>0&&filter.getPrice1()<=4){
			list=ListPrice(filter.getPrice1(), productService.findAllInfoProduct(WebProjectApplication.filterUkulele));
			s=s+" > Giá";
		}
		
		model.addAttribute("infos", list);
		AddAtribute(model);
		model.addAttribute("tittle", s);
		return "/admin/UkuleleAd";
	}

}
