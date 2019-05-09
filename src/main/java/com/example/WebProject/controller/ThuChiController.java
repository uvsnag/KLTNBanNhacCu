package com.example.WebProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebProject.dao.ProductDao;
import com.example.WebProject.entity.CartInfo;
import com.example.WebProject.entity.CartLineInfoView;
import com.example.WebProject.model.ThuChiModel;
import com.example.WebProject.service.CartInfoService;
@Controller
public class ThuChiController {
	
	@Autowired
	private CartInfoService cartInfoService;
	
	@GetMapping("/adtc")
	public String addrum(Model model) {
		
		

		model.addAttribute("model", new ThuChiModel());
		return "/admin/ThuChiAd";
	}
	@RequestMapping("/adtc/check")
	public String function1(Model model, @ModelAttribute("model") @Validated ThuChiModel thuChiModel) {
		
	

		List<CartInfo> list=cartInfoService.findByDateBetween( thuChiModel.getDateStart(),  thuChiModel.getDateEnd());
		//caculSumMoneySell(list,thuChiModel);
		
		System.out.println(thuChiModel.getDateStart());
		int sumMoney=0;
		int revenue=0;
		int sumCostEntry=0;
		
		
		
		for(CartInfo cartInfo: list) {
			
			for(CartLineInfoView cartLineInfoView:cartInfo.getCartLineInfo()) {
				sumCostEntry+=Integer.parseInt(cartLineInfoView.getProduct().getGianhapvao());
				
			} 
			
			
			sumMoney+=cartInfo.getAmount();
			
			
		}
		revenue=sumMoney-sumCostEntry;
		
		thuChiModel.setNumMoneySell((String.valueOf(sumMoney)));
		thuChiModel.setRevenue((String.valueOf(revenue)));
		thuChiModel.setNumBill(String.valueOf(list.size()));
		
		
		System.out.println("list.size()"+list.size());
		model.addAttribute("tittle", "Từ ngày "+thuChiModel.getDateStart()+ " đến "+thuChiModel.getDateEnd());
		model.addAttribute("model", thuChiModel);
		return "/admin/ThuChiAd";
	}

	/*
	 * public ThuChiModel caculSumMoneySell(List<CartInfo> list, ThuChiModel
	 * thuChiModel){
	 * 
	 * int sumMoney=0; for(CartInfo cartInfo: list) {
	 * sumMoney+=cartInfo.getAmount(); } thuChiModel.setNumMoneySell(sumMoney);
	 * return thuChiModel; }
	 */
	
}
