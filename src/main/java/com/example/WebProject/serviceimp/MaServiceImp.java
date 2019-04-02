package com.example.WebProject.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebProject.entity.Ma;
import com.example.WebProject.repository.Marepository;
import com.example.WebProject.service.MaService;
@Service
public class MaServiceImp 	implements MaService {
		
		@Autowired
	    private Marepository maRepository;

		
		  

		    @Override
		    public Ma findOne(int id) {
		        return maRepository.findOne(id);
		    }

		    @Override
		    public void save(Ma contact) {
		    	maRepository.save(contact);
		    }

		   
	}