package com.freemarkerdemo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freemarkerdemo.model.Person;
import com.freemarkerdemo.service.FreemarkerService;
import com.itextpdf.text.DocumentException;

import freemarker.template.TemplateException;

@RestController
public class FileDownloadController {
	
	@Autowired
	FreemarkerService service;
	
	String fileBasePath = "E:\\Jinal's project\\Udemy\\Java\\";
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity downloadFileFromLocal(@PathVariable String fileName, @RequestParam String name) throws IOException, TemplateException, DocumentException {
		//service.createTemplate(name,person);
		//service.createWord();
		//service.createPdf();
		Path path = Paths.get(fileBasePath + fileName);
		Resource resource = new UrlResource(path.toUri());
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@PostMapping("/download/{fileName:.+}")
	public ResponseEntity downloadFile(@PathVariable String fileName, @RequestBody List<Person> persons) 
			throws IOException, TemplateException, DocumentException {
		service.createTemplate(persons);
	//	service.createWord();
		service.createPdf();
		Path path = Paths.get(fileBasePath + fileName);
		Resource resource = new UrlResource(path.toUri());
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
}
