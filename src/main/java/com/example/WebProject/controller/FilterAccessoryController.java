
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
public class FilterAccessoryController {
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
	private void AddAtribute(Model model){ //  in this class this function be showed all other func
		ProductFillter prdFilter=new ProductFillter(-1, -1, -1, -1);
		PriceSearch priceSearch =priceSearchService.findOne(WebProjectApplication.filterAccessory);
		prdFilter.setPriceView1(priceSearch.getPrice1()%10000==0 ? Integer.toString(priceSearch.getPrice1()/1000) 
				: Integer.toString(priceSearch.getPrice1()/1000)+","+Integer.toString((priceSearch.getPrice1()%1000)/100));
		prdFilter.setPriceView2(priceSearch.getPrice2()%1000000==0 ? Integer.toString(priceSearch.getPrice2()/1000000) 
				: Integer.toString(priceSearch.getPrice2()/1000000)+","+Integer.toString((priceSearch.getPrice2()%1000000)/100000));
		prdFilter.setPriceView3(priceSearch.getPrice3()%1000000==0 ? Integer.toString(priceSearch.getPrice3()/1000000) 
				: Integer.toString(priceSearch.getPrice3()/1000000)+","+Integer.toString((priceSearch.getPrice3()%1000000)/100000));
		
		model.addAttribute("filter",prdFilter );
		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterAccessory));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterAccessory));
		model.addAttribute("prnumber", WebProjectApplication.productNumber);
		
	}
	@GetMapping("/accessoryfilterCategory/{id}")
	public String adaccessoryacousticCategory(Model model, @PathVariable int id) {
		
		model.addAttribute("infos", productFilterService.filterCategory(id, productService.findAllInfoProduct(WebProjectApplication.filterAccessory)));
		AddAtribute(model);
		model.addAttribute("tittle", "PHỤ KIỆN >LOẠI");
		return "/accessory";
	}
	@GetMapping("/accessoryfilterProducer/{id}")
	public String adaccessoryacousticProducer(Model model, @PathVariable int id) {
		
		model.addAttribute("infos", productFilterService.filterProducer(id, productService.findAllInfoProduct(WebProjectApplication.filterAccessory)));
		AddAtribute(model);
		model.addAttribute("tittle", "PHỤ KIỆN >HÃNG");
		return "/accessory";
	}
	
	
	@GetMapping("/accessoryfilterPrice/{p1}")
	public String adaccessory1pricer(Model model, @PathVariable int p1) {
		
		model.addAttribute("infos", ListPrice(p1, productService.findAllInfoProduct(WebProjectApplication.filterAccessory)));
		AddAtribute(model);
		model.addAttribute("tittle", "PHỤ KIỆN");
		return "/accessory";
	}
	@GetMapping("/accessoryfilter")
	public String filter(Model model, @Valid ProductFillter filter) {
		String s="PHỤ KIỆN";
		List<ProductInfo> list=productService.findAllInfoProduct(WebProjectApplication.filterAccessory);
		if(filter==null)
		{
			AddAtribute(model);
			model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterAccessory));
			model.addAttribute("tittle", "PHỤ KIỆN ");
			return "accessory";
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
			
			list=ListPrice(filter.getPrice1(), productService.findAllInfoProduct(WebProjectApplication.filterAccessory));
			s=s+" > Giá";
		}
		
		model.addAttribute("infos", list);
		AddAtribute(model);
		model.addAttribute("tittle", s);
		return "/accessory";
	}
	List<ProductInfo> ListPrice(int p1, List<ProductInfo> listaccessoryInfo  ){
		List<ProductInfo> accessoryInfo=new ArrayList<ProductInfo>();
		PriceSearch priceSearch =priceSearchService.findOne(WebProjectApplication.filterAccessory);
		switch(p1){
		case 1: accessoryInfo=productFilterService.filterPrice(1,priceSearch.getPrice1(), listaccessoryInfo);
		break;
		case 2: accessoryInfo=productFilterService.filterPrice(priceSearch.getPrice1(),priceSearch.getPrice2(), listaccessoryInfo);
		break;
		case 3: accessoryInfo=productFilterService.filterPrice(priceSearch.getPrice2(),priceSearch.getPrice3(), listaccessoryInfo);
		break;
		case 4: accessoryInfo=productFilterService.filterPrice(priceSearch.getPrice3(), listaccessoryInfo);
		break;
		}
		return accessoryInfo;
	}
	/*admin*/
	@GetMapping("/adaccessoryfilterCategory/{id}")
	public String FindCategory(Model model, @PathVariable int id) {
		
		model.addAttribute("infos", productFilterService.filterCategory(id, productService.findAllInfoProduct(WebProjectApplication.filterAccessory)));
		AddAtribute(model);
		model.addAttribute("tittle", "PHỤ KIỆN >LOẠI");
		return "/admin/AccessoryAd";
	}
	@GetMapping("/adaccessoryfilterProducer/{id}")
	public String adaccessoryProducer(Model model, @PathVariable int id) {
		
		model.addAttribute("infos",  productFilterService.filterProducer(id, productService.findAllInfoProduct(WebProjectApplication.filterAccessory)));
		AddAtribute(model);
		model.addAttribute("tittle", "PHỤ KIỆN >HÃNG");
		return "/admin/AccessoryAd";
	}

	
	@GetMapping("/adaccessoryfilterPrice/{p1}")
	public String aadaccessory1pricer(Model model, @PathVariable int p1) {
		
		
		
	
		
		model.addAttribute("infos",ListPrice(p1, productService.findAllInfoProduct(WebProjectApplication.filterAccessory)));
		AddAtribute(model);
		model.addAttribute("tittle", "PHỤ KIỆN");
		return "/admin/AccessoryAd";
	}
	@GetMapping("/adaccessoryfilter")
	public String afilter(Model model, @Valid ProductFillter filter) {
		String s="PHỤ KIỆN";
		List<ProductInfo> list=productService.findAllInfoProduct(WebProjectApplication.filterAccessory);
		if(filter==null)
		{
			AddAtribute(model);
			model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterAccessory));
		
			return "admin/AccessoryAd";
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
			list=ListPrice(filter.getPrice1(), productService.findAllInfoProduct(WebProjectApplication.filterAccessory));
			s=s+" > Giá";
		}
		
		model.addAttribute("infos", list);
		AddAtribute(model);
		model.addAttribute("tittle", s);
		return "/admin/AccessoryAd";
	}

}
