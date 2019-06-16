package com.example.WebProject.service;

import java.sql.Date;
import java.util.List;

import com.example.WebProject.entity.HoaDonNhap;

public interface HoaDonNhapService {

	//Iterable<HoaDonNhap> findAll();

 
	HoaDonNhap findOne(int id);

    void save(HoaDonNhap contact);

    void delete(int id);
    public List<HoaDonNhap> findByDateBetween(Date from, Date to);
	
}