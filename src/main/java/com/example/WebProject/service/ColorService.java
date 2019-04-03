package com.example.WebProject.service;


import java.util.List;

import com.example.WebProject.entity.Color;

public interface ColorService {

	Iterable<Color> findAll();

 
	Color findOne(int id);

    void save(Color contact);

    void delete(int id);

	
	List<Color> findByNameContaining(String q);
}
