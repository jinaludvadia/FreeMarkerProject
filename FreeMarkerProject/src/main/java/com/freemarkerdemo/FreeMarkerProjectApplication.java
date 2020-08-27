package com.freemarkerdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.freemarkerdemo.service.FreemarkerService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class FreeMarkerProjectApplication{

	@Autowired
	FreemarkerService service;
	
	public static void main(String[] args) {
		
		SpringApplication.run(FreeMarkerProjectApplication.class, args);
		
	}
	
	/*
	@Override
	public void run(String... args) throws Exception {
		service.createTemplate(Collections.singletonList(new Person("Jinal","Mumbai")));
	//	service.createWord();
		service.createPdf();
		
	}*/
}