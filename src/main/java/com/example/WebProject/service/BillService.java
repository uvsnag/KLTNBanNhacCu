package com.example.WebProject.service;

import java.util.List;

import com.example.WebProject.model.BillInfo;
import com.example.WebProject.model.CartLineModel;

public interface BillService {
	int CountBill(int idConfirm);
	List<BillInfo> findAllOrder();
	 List<BillInfo> findBillConfirm(int numberComfirm);
	 List<CartLineModel> findBillDetail(int idCart);
}
