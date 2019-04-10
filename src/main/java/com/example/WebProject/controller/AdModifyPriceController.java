package com.example.WebProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WebProject.entity.PriceSearch;
import com.example.WebProject.service.CategoryService;
import com.example.WebProject.service.PriceSearchService;

@Controller
public class AdModifyPriceController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PriceSearchService priceSearchService;
	
	@GetMapping("/editprice/{id}")
	public String search(Model model, @PathVariable int id) {
		model.addAttribute("tittle", "Sửa bảng giá "+categoryService.findOne(id).getCategory());

		model.addAttribute("contact", priceSearchService.findOne(id));
	
		return "/admin/PriceEdit";
	}
	
	@PostMapping("/adprice/save")
	public String save(Model model, @ModelAttribute("contact") @Validated PriceSearch contact, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			
			
			return "/admin/adguitar";
		}

		
		priceSearchService.save(contact);
		
		redirect.addFlashAttribute("success", "Đã thay đổi mức giá mới!");
		return "redirect:/adguitar";
	}
	
}
