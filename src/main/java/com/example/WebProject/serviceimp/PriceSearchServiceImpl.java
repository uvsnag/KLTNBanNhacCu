
package com.example.WebProject.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebProject.entity.PriceSearch;
import com.example.WebProject.repository.PriceSearchRepository;
import com.example.WebProject.service.PriceSearchService;

@Service
public class PriceSearchServiceImpl implements PriceSearchService {
	
	@Autowired
    private PriceSearchRepository priceSearchRepository;

	
	  @Override
	    public Iterable<PriceSearch> findAll() {
	        return priceSearchRepository.findAll();
	    }

	  

	    @Override
	    public PriceSearch findOne(int id) {
	        return priceSearchRepository.findOne(id);
	    }

	    @Override
	    public void save(PriceSearch contact) {
	    	priceSearchRepository.save(contact);
	    }

	    @Override
	    public void delete(int id) {
	    	priceSearchRepository.delete(id);
	    }
	  
}
