package com.example.WebProject.serviceimp;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebProject.entity.CartInfo;
import com.example.WebProject.entity.HoaDonNhap;
import com.example.WebProject.repository.HoaDonNhapRepository;
import com.example.WebProject.service.HoaDonNhapService;

@Service
public class HoaDonNhapServiceImpl 	implements HoaDonNhapService {
		
		@Autowired
	    private HoaDonNhapRepository HoaDonNhapRepository;

		
	/*
	 * @Override public Iterable<HoaDonNhapService> findAll() { return
	 * HoaDonNhapRepository.findAll(); }
	 */
		   
		    @Override
		    public HoaDonNhap findOne(int id) {
		        return HoaDonNhapRepository.findOne(id);
		    }

		    @Override
		    public void save(HoaDonNhap contact) {
		    	HoaDonNhapRepository.save(contact);
		    }
		    
		    @Override
		    public void delete(int id) {
		    	HoaDonNhapRepository.delete(id);
		    }
		    public List<HoaDonNhap> findByDateBetween(Date from, Date to) {
		        return HoaDonNhapRepository.findByDateBetween( from,  to);
		    }
	}
