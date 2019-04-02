

package com.example.WebProject.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebProject.entity.CommentRate;
import com.example.WebProject.entity.CommentRateIndentity;
import com.example.WebProject.repository.CommentRateRepository;
import com.example.WebProject.service.CommentRateService;

@Service
public class CommentRateServiceImp implements CommentRateService {
	
	@Autowired
    private CommentRateRepository commentRateRepository;
	 
	
	  @Override
	    public Iterable<CommentRate> findAll() {
	        return commentRateRepository.findAll();
	    }

	

	    @Override
	    public CommentRate findOne(CommentRateIndentity id) {
	        return commentRateRepository.findOne(id);
	    }

	    @Override
	    public void save(CommentRate contact) {
	    	commentRateRepository.save(contact);
	    }

	    @Override
	    public void delete(CommentRateIndentity id) {
	    	commentRateRepository.delete(id);
	    }
	    
}
