package com.example.WebProject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.example.WebProject.model.CartLineModel;

@SpringBootApplication




public class WebProjectApplication {
	
	public static int productNumber=0;//number productions on the bar menu
	public static int filterGuitar=1;
	public static int filterPiano=2;
	public static int filterDrum=3;
	public static int filterFlute=4;
	public static int filterAccessory=5;
	public static int filterUkulele=6;
	public static int indexProduct=0;// index of product-to delete
	public static List<CartLineModel> listCartLine=new ArrayList<CartLineModel>();
	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}
	 @Bean
	    public MessageSource messageSource() {
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        // Tải file: validation.properties
	        messageSource.setBasename("classpath:validation");
	       
	        //messageSource.setBasename("validation");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;}
	
	

}
