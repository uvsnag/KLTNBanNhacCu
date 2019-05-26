package com.example.WebProject.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.WebProject.entity.HoaDonNhap;

public interface HoaDonNhapRepository extends CrudRepository<HoaDonNhap, Integer> {
	public List<HoaDonNhap> findByDateBetween(Date from, Date to);
}