
package com.example.WebProject.service;

import com.example.WebProject.entity.PriceSearch;

public interface PriceSearchService {

	Iterable<PriceSearch> findAll();

	PriceSearch findOne(int id);

    void save(PriceSearch contact);
  
    void delete(int id);
   
}

