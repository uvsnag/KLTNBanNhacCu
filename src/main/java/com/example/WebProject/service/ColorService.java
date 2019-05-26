package com.example.WebProject.service;


import com.example.WebProject.entity.Color;

public interface ColorService {

	Iterable<Color> findAll();

 
	Color findOne(int id);

    void save(Color contact);

    void delete(int id);

	
	Color findByNameContaining(String q);
}
