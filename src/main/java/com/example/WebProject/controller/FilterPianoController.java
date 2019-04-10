
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
public class FilterPianoController {
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
		PriceSearch priceSearch =priceSearchService.findOne(WebProjectApplication.filterPiano);
		prdFilter.setPriceView1(priceSearch.getPrice1()%1000000==0 ? Integer.toString(priceSearch.getPrice1()/1000000) 
				: Integer.toString(priceSearch.getPrice1()/1000000)+","+Integer.toString((priceSearch.getPrice1()%1000000)/100000));
		prdFilter.setPriceView2(priceSearch.getPrice2()%1000000==0 ? Integer.toString(priceSearch.getPrice2()/1000000) 
				: Integer.toString(priceSearch.getPrice2()/1000000)+","+Integer.toString((priceSearch.getPrice2()%1000000)/100000));
		prdFilter.setPriceView3(priceSearch.getPrice3()%1000000==0 ? Integer.toString(priceSearch.getPrice3()/1000000) 
				: Integer.toString(priceSearch.getPrice3()/1000000)+","+Integer.toString((priceSearch.getPrice3()%1000000)/100000));
		
		model.addAttribute("filter",prdFilter );
		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterPiano));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterPiano));
		model.addAttribute("prnumber", WebProjectApplication.productNumber);
		
	}
	@GetMapping("/pianofilterCategory/{id}")
	public String adpianoacousticCategory(Model model, @PathVariable int id) {
		
		model.addAttribute("infos", productFilterService.filterCategory(id, productService.findAllInfoProduct(WebProjectApplication.filterPiano)));
		AddAtribute(model);
		model.addAttribute("tittle", "PIANO >LOẠI");
		return "/piano";
	}
	@GetMapping("/pianofilterProducer/{id}")
	public String adpianoacousticProducer(Model model, @PathVariable int id) {
		
		model.addAttribute("infos", productFilterService.filterProducer(id, productService.findAllInfoProduct(WebProjectApplication.filterPiano)));
		AddAtribute(model);
		model.addAttribute("tittle", "PIANO >HÃNG");
		return "/piano";
	}
	
	
	@GetMapping("/pianofilterPrice/{p1}")
	public String adpiano1pricer(Model model, @PathVariable int p1) {
		
		model.addAttribute("infos", ListPrice(p1, productService.findAllInfoProduct(WebProjectApplication.filterPiano)));
		AddAtribute(model);
		model.addAttribute("tittle", "PIANO");
		return "/piano";
	}
	@GetMapping("/pianofilter")
	public String filter(Model model, @Valid ProductFillter filter) {
		String s="PIANO";
		List<ProductInfo> list=productService.findAllInfoProduct(WebProjectApplication.filterPiano);
		if(filter==null)
		{
			AddAtribute(model);
			model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterPiano));
			model.addAttribute("tittle", "PIANO ");
			return "piano";
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
			
			list=ListPrice(filter.getPrice1(), productService.findAllInfoProduct(WebProjectApplication.filterPiano));
			s=s+" > Giá";
		}
		
		model.addAttribute("infos", list);
		AddAtribute(model);
		model.addAttribute("tittle", s);
		return "/piano";
	}
	List<ProductInfo> ListPrice(int p1, List<ProductInfo> listpianoInfo  ){
		List<ProductInfo> pianoInfo=new ArrayList<ProductInfo>();
		PriceSearch priceSearch =priceSearchService.findOne(WebProjectApplication.filterPiano);
		switch(p1){
		case 1: pianoInfo=productFilterService.filterPrice(1,5000000, listpianoInfo);
		break;
		case 2: pianoInfo=productFilterService.filterPrice(priceSearch.getPrice1(),priceSearch.getPrice2(), listpianoInfo);
		break;
		case 3: pianoInfo=productFilterService.filterPrice(priceSearch.getPrice2(),priceSearch.getPrice3(), listpianoInfo);
		break;
		case 4: pianoInfo=productFilterService.filterPrice(priceSearch.getPrice3(), listpianoInfo);
		break;
		}
		return pianoInfo;
	}
	/*admin*/
	@GetMapping("/adpianofilterCategory/{id}")
	public String FindCategory(Model model, @PathVariable int id) {
		
		model.addAttribute("infos", productFilterService.filterCategory(id, productService.findAllInfoProduct(WebProjectApplication.filterPiano)));
		AddAtribute(model);
		model.addAttribute("tittle", "PIANO >LOẠI");
		return "/admin/PianoAd";
	}
	@GetMapping("/adpianofilterProducer/{id}")
	public String adpianoProducer(Model model, @PathVariable int id) {
		
		model.addAttribute("infos",  productFilterService.filterProducer(id, productService.findAllInfoProduct(WebProjectApplication.filterPiano)));
		AddAtribute(model);
		model.addAttribute("tittle", "PIANO >HÃNG");
		return "/admin/PianoAd";
	}

	
	@GetMapping("/adpianofilterPrice/{p1}")
	public String aadpiano1pricer(Model model, @PathVariable int p1) {
		
		
		
	
		
		model.addAttribute("infos",ListPrice(p1, productService.findAllInfoProduct(WebProjectApplication.filterPiano)));
		AddAtribute(model);
		model.addAttribute("tittle", "PIANO");
		return "/admin/PianoAd";
	}
	@GetMapping("/adpianofilter")
	public String afilter(Model model, @Valid ProductFillter filter) {
		String s="PIANO";
		List<ProductInfo> list=productService.findAllInfoProduct(WebProjectApplication.filterPiano);
		if(filter==null)
		{
			AddAtribute(model);
			model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterPiano));
		
			return "admin/PianoAd";
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
			list=ListPrice(filter.getPrice1(), productService.findAllInfoProduct(WebProjectApplication.filterPiano));
			s=s+" > Giá";
		}
		
		model.addAttribute("infos", list);
		AddAtribute(model);
		model.addAttribute("tittle", s);
		return "/admin/PianoAd";
	}

}
