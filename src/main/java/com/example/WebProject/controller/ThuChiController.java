package com.example.WebProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ThuChiController {

	
	
	@GetMapping("/adtc")
	public String addrum(Model model) {
		
		model.addAttribute("tittle", "QUẢN LÝ THU CHI");
		
		return "/admin/ThuChiAd";
	}

}
