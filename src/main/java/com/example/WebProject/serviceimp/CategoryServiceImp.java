package com.example.WebProject.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebProject.entity.Category;
import com.example.WebProject.repository.CategoryRepository;
import com.example.WebProject.service.CategoryService;
@Service
public class CategoryServiceImp 	implements CategoryService {
		
		@Autowired
	    private CategoryRepository categoryRepository;

		
		  @Override
		    public Iterable<Category> findAll() {
		        return categoryRepository.findAll();
		    }

		    @Override
		    public List<Category> search(String q) {
		        return categoryRepository.findByCategoryContaining(q);
		    }

		    @Override
		    public Category findOne(int id) {
		        return categoryRepository.findOne(id);
		    }

		    @Override
		    public void save(Category contact) {
		    	categoryRepository.save(contact);
		    }

		    @Override
		    public void delete(int id) {
		    	categoryRepository.delete(id);
		    	
		    }
		    @Override
		    public  Category findByCategoryContaining(String q){
		    	Category result=new Category();
		    	for(Category cl:categoryRepository.findAll()) {
					if(q.equals(cl.getCategory())) {
						result= cl;
					}
				}
		    	return result;
		    }
		  
	}