
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
public class FilterGuitarController {
	@Autowired
	private ProductFilterService productFilterService;
	@Autowired
	private PriceSearchService priceSearchService;
	@Autowired
	private ProductService productService;

	@Autowired
	
	private ProducerService producerService;
	
	@Autowired
	private Category2Service category2Service;
	
	private void AddAtribute(Model model){
		ProductFillter prdFilter=new ProductFillter(-1, -1, -1, -1);
		PriceSearch priceSearch =priceSearchService.findOne(WebProjectApplication.filterGuitar);
		prdFilter.setPriceView1(priceSearch.getPrice1()%1000000==0 ? Integer.toString(priceSearch.getPrice1()/1000000) 
				: Integer.toString(priceSearch.getPrice1()/1000000)+","+Integer.toString((priceSearch.getPrice1()%1000000)/100000));
		prdFilter.setPriceView2(priceSearch.getPrice2()%1000000==0 ? Integer.toString(priceSearch.getPrice2()/1000000) 
				: Integer.toString(priceSearch.getPrice2()/1000000)+","+Integer.toString((priceSearch.getPrice2()%1000000)/100000));
		prdFilter.setPriceView3(priceSearch.getPrice3()%1000000==0 ? Integer.toString(priceSearch.getPrice3()/1000000) 
				: Integer.toString(priceSearch.getPrice3()/1000000)+","+Integer.toString((priceSearch.getPrice3()%1000000)/100000));
		
		model.addAttribute("filter",prdFilter );
		
		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterGuitar));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterGuitar));
		model.addAttribute("prnumber", WebProjectApplication.productNumber);
		
	}
	
	@GetMapping("/guitarfilterCategory/{id}")
	public String adguitaracousticCategory(Model model, @PathVariable int id) {
		
		model.addAttribute("guitars", productFilterService.filterCategory(id, productService.findAllInfoProduct(WebProjectApplication.filterGuitar)));
		AddAtribute(model);
		model.addAttribute("tittle", "GUITAR >LOẠI");
		return "/guitar";
	}
	@GetMapping("/guitarfilterProducer/{id}")
	public String adguitaracousticProducer(Model model, @PathVariable int id) {
		
		model.addAttribute("guitars", productFilterService.filterProducer(id, productService.findAllInfoProduct(WebProjectApplication.filterGuitar)));
		AddAtribute(model);
		model.addAttribute("tittle", "GUITAR >HÃNG");
		return "/guitar";
	}
	
	
	@GetMapping("/guitarfilterPrice/{p1}")
	public String adguitar1pricer(Model model, @PathVariable int p1) {

		model.addAttribute("guitars",ListPrice(p1, productService.findAllInfoProduct(WebProjectApplication.filterGuitar)));
		AddAtribute(model);
		model.addAttribute("tittle", "GUITAR");
		return "/guitar";
	}
	@GetMapping("/guitarfilter")
	public String filter(Model model, @Valid ProductFillter filter) {
		String s="GUITAR";
		List<ProductInfo> list=productService.findAllInfoProduct(WebProjectApplication.filterGuitar);
		if(filter==null)
		{
			AddAtribute(model);
			model.addAttribute("guitars", productService.findAllInfoProduct(WebProjectApplication.filterGuitar));
			model.addAttribute("tittle", "GUITAR ");
			return "guitar";
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
			
			list=ListPrice(filter.getPrice1(), productService.findAllInfoProduct(WebProjectApplication.filterGuitar));
			s=s+" > Giá";
		}
		
		model.addAttribute("guitars", list);
		AddAtribute(model);
		model.addAttribute("tittle", s);
		return "/guitar";
	}
	List<ProductInfo> ListPrice(int p1, List<ProductInfo> listguitarInfo  ){
		List<ProductInfo> guitarInfo=new ArrayList<ProductInfo>();
		PriceSearch priceSearch =priceSearchService.findOne(WebProjectApplication.filterGuitar);
		switch(p1){
		case 1: guitarInfo=productFilterService.filterPrice(1,priceSearch.getPrice1(), listguitarInfo);
		break;
		case 2: guitarInfo=productFilterService.filterPrice(priceSearch.getPrice1(),priceSearch.getPrice2(), listguitarInfo);
		break;
		case 3: guitarInfo=productFilterService.filterPrice(priceSearch.getPrice2(),priceSearch.getPrice3(), listguitarInfo);
		break;
		case 4: guitarInfo=productFilterService.filterPrice(priceSearch.getPrice3(), listguitarInfo);
		break;
		}
		return guitarInfo;
	}
	
	/*admin*/
	@GetMapping("/adguitarfilterCategory/{id}")
	public String FindCategory(Model model, @PathVariable int id) {
	
		model.addAttribute("guitars", productFilterService.filterCategory(id, productService.findAllInfoProduct(WebProjectApplication.filterGuitar)));
		AddAtribute(model);
		model.addAttribute("tittle", "GUITAR >LOẠI");
		return "/admin/GuitarAd";
	}
	@GetMapping("/adguitarfilterProducer/{id}")
	public String adguitarProducer(Model model, @PathVariable int id) {
		
		model.addAttribute("guitars", productFilterService.filterProducer(id, productService.findAllInfoProduct(WebProjectApplication.filterGuitar)));
		AddAtribute(model);
		model.addAttribute("tittle", "GUITAR >HÃNG");
		return "/admin/GuitarAd";
	}

	
	@GetMapping("/adguitarfilterPrice/{p1}")
	public String aadguitar1pricer(Model model, @PathVariable int p1) {
		
		
		
	
		
		model.addAttribute("guitars",ListPrice(p1, productService.findAllInfoProduct(WebProjectApplication.filterGuitar)));
		AddAtribute(model);
		model.addAttribute("tittle", "GUITAR");
		return "/admin/GuitarAd";
	}
	@GetMapping("/adguitarfilter")
	public String afilter(Model model, @Valid ProductFillter filter) {
		String s="GUITAR";
		List<ProductInfo> list=productService.findAllInfoProduct(WebProjectApplication.filterGuitar);
		if(filter==null)
		{
			AddAtribute(model);
			model.addAttribute("guitars", productService.findAllInfoProduct(WebProjectApplication.filterGuitar));
			model.addAttribute("tittle", "GUITAR ");
			return "admin/GuitarAd";
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
			list=ListPrice(filter.getPrice1(), productService.findAllInfoProduct(WebProjectApplication.filterGuitar));
			s=s+" > Giá";
		}
		
		model.addAttribute("guitars", list);
		AddAtribute(model);
		model.addAttribute("tittle", s);
		return "/admin/GuitarAd";
	}
		
}
