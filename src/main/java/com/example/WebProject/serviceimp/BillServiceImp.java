package com.example.WebProject.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebProject.dao.BillDao;
import com.example.WebProject.model.BillInfo;
import com.example.WebProject.model.CartLineModel;
import com.example.WebProject.service.BillService;
@Service
public class BillServiceImp implements BillService{

	@Autowired
    private BillDao billDao;
	
	@Override
	public int CountBill(int idConfirm) {
		return billDao.CountBill(idConfirm);
	}

	@Override
	public List<BillInfo> findAllOrder() {
		return billDao.findAllOrder();
	}

	@Override
	public List<BillInfo> findBillConfirm(int numberComfirm) {
		return billDao.findBillConfirm(numberComfirm);
	}

	@Override
	public List<CartLineModel> findBillDetail(int idCart) {
		return billDao.findBillDetail(idCart);
	}

}
