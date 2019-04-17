package com.example.WebProject.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WebProject.WebProjectApplication;
import com.example.WebProject.entity.Comment;
import com.example.WebProject.entity.PriceSearch;
import com.example.WebProject.model.CommentModel;
import com.example.WebProject.model.ProductFillter;
import com.example.WebProject.service.BillService;
import com.example.WebProject.service.Category2Service;
import com.example.WebProject.service.CommentService;
import com.example.WebProject.service.PriceSearchService;
import com.example.WebProject.service.ProductFilterService;
import com.example.WebProject.service.ProductService;

@Controller
public class AdViewController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private PriceSearchService priceSearchService;
	@Autowired
	private ProductFilterService productFilterService;
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private BillService billService;
	@Autowired
	private Category2Service category2Service;
	

	private void PrAddModel(Model model) {
		model.addAttribute("filter", new ProductFillter(-1, -1, -1, -1));

	}

	private void AddNotify(Model model) {
		model.addAttribute("notconfirm", billService.CountBill(0));
		model.addAttribute("confirmed", billService.CountBill(1));
		model.addAttribute("comment", commentService.findByNoteContaining(0).size());
	}
	@GetMapping("/notifycomment")
	public String notify(Model model) {
		AddNotify(model);
		model.addAttribute("comments",commentService.findByNoteContaining(0));
		
		return "/admin/_notify";
	}
	@GetMapping("/seencomment/{id}")
	public String seenComment(@PathVariable int id, Model model, RedirectAttributes redirect) {
		Comment cmt=commentService.findOne(id);
		cmt.setNote(1);
		commentService.save(cmt);
		redirect.addFlashAttribute("success", "Đã đánh dấu!");
		return "redirect:/notifycomment";
	
	}

	
	@GetMapping("/_")
	public String index(Model model) {
		AddNotify(model);
		model.addAttribute("guitars", productService.listProductInfo(WebProjectApplication.filterGuitar));
		model.addAttribute("pianos", productService.listProductInfo(WebProjectApplication.filterPiano));
		model.addAttribute("drums", productService.listProductInfo(WebProjectApplication.filterDrum));
		model.addAttribute("ukuleles", productService.listProductInfo(WebProjectApplication.filterUkulele));

		return "/admin/_home";
	}

	@GetMapping("/_guitar")
	public String bbb(Model model) {
		AddNotify(model);
		model.addAttribute("tittle", "GUITAR");

		model.addAttribute("guitars", productService.findAllInfoProduct(WebProjectApplication.filterGuitar));
		PrAddModel(model);

		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterGuitar));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterGuitar));

		return "/admin/_guitar";
	}

	@GetMapping("/_ukulele")
	public String Ukulele(Model model) {
		model.addAttribute("tittle", "UKULELE");
		PrAddModel(model);
		AddNotify(model);
		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterUkulele));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterUkulele));
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterUkulele));

		return "/admin/_ukulele";
	}

	@GetMapping("/_piano")
	public String Piano(Model model) {
		model.addAttribute("tittle", "PIANO");
		PrAddModel(model);
		AddNotify(model);
		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterPiano));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterPiano));
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterPiano));

		return "/admin/_piano";
	}

	@GetMapping("/_drum")
	public String Drum(Model model) {
		model.addAttribute("tittle", "TRỐNG");
		PrAddModel(model);
		AddNotify(model);
		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterDrum));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterDrum));
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterDrum));

		return "/admin/_drum";
	}

	@GetMapping("/_flute")
	public String uku(Model model) {
		model.addAttribute("tittle", "SÁO TRÚC");
		PrAddModel(model);
		AddNotify(model);
		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterFlute));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterFlute));
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterFlute));
		return "/admin/_flute";
	}

	//
	@GetMapping("/_accessory")
	public String acc(Model model) {
		model.addAttribute("tittle", "PHỤ KIỆN");
		PrAddModel(model);
		AddNotify(model);
		model.addAttribute("producers", productFilterService.list5Producer(WebProjectApplication.filterAccessory));
		model.addAttribute("categories", category2Service.findByIdcpContaining(WebProjectApplication.filterAccessory));
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterAccessory));
		return "/admin/_accessory";
	}
	//

	@GetMapping("/_product/{id}")
	public String Show1item(@PathVariable int id, Model model) {
		AddNotify(model);
		ShowProduct(model, id);

		return "/admin/AdViewProduct";
	}

	void ShowProduct(Model model, int id) {// add model to show page product
											// admin
		model.addAttribute("info", productService.findProductInfo(id));
		model.addAttribute("commentrate", productService.Get10Comment(id));

		// comment
		Set<Comment> scomment = productService.findOne(id).getComment();// get
																		// set
																		// comment
		model.addAttribute("comments", scomment);// many comments to display
		// add id product into id comment to save then set value right with
		// entity Ma
		CommentModel cmt = new CommentModel(id);
		cmt.setName("SMUSIC");
		cmt.setPhone("1111111111");
		model.addAttribute("onecomment", cmt);// one comment to
												// save
		model.addAttribute("totalcomment", scomment.size());// one comment to
															// save
	}

	@GetMapping("/adcomment/{id}/delete") /* id1: id product, id2 id comment */
	public String Color(@PathVariable int id, RedirectAttributes redirect) {
		commentService.delete(id);

		redirect.addFlashAttribute("success", "Xóa bình luận thành công!");
		return "redirect:/result";
	}

	@GetMapping("/result")
	public String Result() {

		return "/result";
	}

	///////////////////////////////////////////
	// view
	private void AddAtribute(Model model, int id) {// id: id category of product
		ProductFillter prdFilter=new ProductFillter(-1, -1, -1, -1);
		PriceSearch priceSearch =priceSearchService.findOne(id);
		prdFilter.setPriceView1(priceSearch.getPrice1()%1000000==0 ? Integer.toString(priceSearch.getPrice1()/1000000) 
				: Integer.toString(priceSearch.getPrice1()/1000000)+","+Integer.toString((priceSearch.getPrice1()%1000000)/100000));
		prdFilter.setPriceView2(priceSearch.getPrice2()%1000000==0 ? Integer.toString(priceSearch.getPrice2()/1000000) 
				: Integer.toString(priceSearch.getPrice2()/1000000)+","+Integer.toString((priceSearch.getPrice2()%1000000)/100000));
		prdFilter.setPriceView3(priceSearch.getPrice3()%1000000==0 ? Integer.toString(priceSearch.getPrice3()/1000000) 
				: Integer.toString(priceSearch.getPrice3()/1000000)+","+Integer.toString((priceSearch.getPrice3()%1000000)/100000));
		
		model.addAttribute("filter",prdFilter );
		
		model.addAttribute("producers", productFilterService.list5Producer(id));
		model.addAttribute("categories", category2Service.findByIdcpContaining(id));
	}
	private void AddAtribute2(Model model, int id) {// id: id category of product
		ProductFillter prdFilter=new ProductFillter(-1, -1, -1, -1);
		PriceSearch priceSearch =priceSearchService.findOne(id);
		prdFilter.setPriceView1(priceSearch.getPrice1()%10000==0 ? Integer.toString(priceSearch.getPrice1()/1000) 
				: Integer.toString(priceSearch.getPrice1()/1000)+","+Integer.toString((priceSearch.getPrice1()%1000)/100));
		prdFilter.setPriceView2(priceSearch.getPrice2()%1000000==0 ? Integer.toString(priceSearch.getPrice2()/1000000) 
				: Integer.toString(priceSearch.getPrice2()/1000000)+","+Integer.toString((priceSearch.getPrice2()%1000000)/100000));
		prdFilter.setPriceView3(priceSearch.getPrice3()%1000000==0 ? Integer.toString(priceSearch.getPrice3()/1000000) 
				: Integer.toString(priceSearch.getPrice3()/1000000)+","+Integer.toString((priceSearch.getPrice3()%1000000)/100000));
		
		model.addAttribute("filter",prdFilter );
		
		model.addAttribute("producers", productFilterService.list5Producer(id));
		model.addAttribute("categories", category2Service.findByIdcpContaining(id));
	}

	/* view accessory */
	@GetMapping("/adaccessory")
	public String adaccessory(Model model) {
		AddAtribute2(model, WebProjectApplication.filterAccessory);
		AddNotify(model);
		model.addAttribute("tittle", "PHỤ KIỆN");
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterAccessory));
		return "/admin/AccessoryAd";
	}

	/* view drum */
	@GetMapping("/addrum")
	public String addrum(Model model) {
		AddAtribute(model, WebProjectApplication.filterDrum);
		AddNotify(model);
		model.addAttribute("tittle", "TRỐNG");
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterDrum));
		return "/admin/DrumAd";
	}

	/* view flute */
	@GetMapping("/adflute")
	public String adflute(Model model) {
		AddAtribute2(model, WebProjectApplication.filterFlute);
		AddNotify(model);
		model.addAttribute("tittle", "SÁO TRÚC");
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterFlute));
		return "/admin/FluteAd";
	}

	/* view guitar */
	@GetMapping("/adguitar")
	public String adguitar(Model model) {
		AddAtribute(model, WebProjectApplication.filterGuitar);
		AddNotify(model);
		model.addAttribute("tittle", "GUITAR");
		model.addAttribute("guitars", productService.findAllInfoProduct(WebProjectApplication.filterGuitar));
		return "/admin/GuitarAd";
	}

	/* view piano */
	@GetMapping("/adpiano")
	public String adpiano(Model model) {
		AddAtribute(model, WebProjectApplication.filterPiano);
		AddNotify(model);
		model.addAttribute("tittle", "PIANO");
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterPiano));
		return "/admin/PianoAd";
	}

	/* view ukulele */
	@GetMapping("/adukulele")
	public String adukulele(Model model) {
		AddAtribute2(model, WebProjectApplication.filterUkulele);
		AddNotify(model);
		model.addAttribute("tittle", "UKULELE");
		model.addAttribute("infos", productService.findAllInfoProduct(WebProjectApplication.filterUkulele));
		return "/admin/UkuleleAd";
	}
}
