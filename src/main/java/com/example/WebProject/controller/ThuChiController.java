package com.example.WebProject.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebProject.entity.CartInfo;
import com.example.WebProject.entity.CartLineInfoView;
import com.example.WebProject.entity.HoaDonNhap;
import com.example.WebProject.model.ThuChiModel;
import com.example.WebProject.service.CartInfoService;
import com.example.WebProject.service.HoaDonNhapService;

@Controller
public class ThuChiController {

	@Autowired
	private CartInfoService cartInfoService;
	@Autowired
	private HoaDonNhapService hoaDonNhapService;

	@GetMapping("/adtc")
	public String addrum(Model model) {
		ThuChiModel thuChiModel = new ThuChiModel();
		String str = "2017-01-1";

		Date dateStart = Date.valueOf(str);
		str = "2020-01-1";
		Date dateEnd = Date.valueOf(str);
		thuChiModel.setDateStart(dateStart);
		thuChiModel.setDateEnd(dateEnd);

		model.addAttribute("model", thuChiModel);
		return "/admin/ThuChiAd";
	}

	@RequestMapping("/adtc/check")
	public String function1(Model model, @ModelAttribute("model") @Validated ThuChiModel thuChiModel) {

		processBill(model, thuChiModel);
		return "/admin/ThuChiAd";
	}
	@RequestMapping("/adtc/checkdate")
	public String functionDate(Model model, @ModelAttribute("model") @Validated ThuChiModel thuChiModel) {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date date = new java.sql.Date(cal.getTimeInMillis());	
		 		
		thuChiModel.setDateStart(date);
		
		cal = Calendar.getInstance();
		
		cal.add(Calendar.DAY_OF_MONTH, 1);
		date = new java.sql.Date(cal.getTimeInMillis());
		thuChiModel.setDateEnd(date);

		 
		processBill(model, thuChiModel);
		return "/admin/ThuChiAd";
	}
	@RequestMapping("/adtc/checkmonth")
	public String function(Model model, @ModelAttribute("model") @Validated ThuChiModel thuChiModel) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date = new java.sql.Date(cal.getTimeInMillis());	
		 		
		thuChiModel.setDateStart(date);
		
		cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		date = new java.sql.Date(cal.getTimeInMillis());
		thuChiModel.setDateEnd(date);

		 
		processBill(model, thuChiModel);
		return "/admin/ThuChiAd";
	}
	@RequestMapping("/adtc/checkyear")
	public String functionyear(Model model, @ModelAttribute("model") @Validated ThuChiModel thuChiModel) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 1);
		Date date = new java.sql.Date(cal.getTimeInMillis());	
		 		
		thuChiModel.setDateStart(date);
		
		cal = Calendar.getInstance();
		
		date = new java.sql.Date(cal.getTimeInMillis());
		thuChiModel.setDateEnd(date);

		 
		processBill(model, thuChiModel);
		return "/admin/ThuChiAd";
	}
	public void processBill(Model model, ThuChiModel thuChiModel) {
		List<HoaDonNhap> lhdn = hoaDonNhapService.findByDateBetween(thuChiModel.getDateStart(),
				thuChiModel.getDateEnd());
		List<CartInfo> list = cartInfoService.findByDateBetween(thuChiModel.getDateStart(), thuChiModel.getDateEnd());
		// caculSumMoneySell(list,thuChiModel);

		System.out.println(thuChiModel.getDateStart());
		int sumMoney = 0;
		int revenue = 0;
		int sumCostEntry = 0;
		int numPurchaseMoney = 0;

		for (CartInfo cartInfo : list) {

			for (CartLineInfoView cartLineInfoView : cartInfo.getCartLineInfo()) {
				sumCostEntry += Integer.parseInt(cartLineInfoView.getProduct().getGianhapvao());

			}
			sumMoney += cartInfo.getAmount();
		}
		revenue = sumMoney - sumCostEntry;

		for (HoaDonNhap hdn : lhdn) {

			numPurchaseMoney += hdn.getNumMoney();
		}
		thuChiModel.setNumMoneyBuy((String.valueOf(numPurchaseMoney)));
		thuChiModel.setNumMoneySell((String.valueOf(sumMoney)));
		thuChiModel.setRevenue((String.valueOf(revenue)));
		thuChiModel.setNumBill(String.valueOf(list.size()));

		System.out.println("list.size()" + list.size());
		model.addAttribute("tittle", "Từ ngày " + thuChiModel.getDateStart() + " đến " + thuChiModel.getDateEnd());
		model.addAttribute("model", thuChiModel);
	}

}
