package com.freemarkerdemo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.freemarkerdemo.model.Person;
import com.freemarkerdemo.model.Product;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Service
public class FreemarkerService {
	
	private Template template;
	private Map map;

	public void createTemplate(List<Person> persons) throws IOException, TemplateException {
		
		//FreeMarker configuration object
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
		
		// Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(true);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

		//Load template from source folder
		cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\lenovo\\git\\FreeMarkerProject\\FreeMarkerProject\\src\\main\\resources\\templates"));

		//Get Template
		template = cfg.getTemplate("hello.ftl");

		//Create data-model
		map = new HashMap();
		map.put("persons", persons);
		
	}
	
	public void createWord() throws IOException, TemplateException {
		OutputStream os = new FileOutputStream("E:\\Jinal's project\\Udemy\\Java\\WordExample.doc");
		Writer writer = new OutputStreamWriter(os);
		template.process(map, writer);
		
		
		//Code for docx file
	/*	//Parse HTML
		Document doc = Jsoup.parse(html);
		//System.out.println(doc.wholeText());
		//Save it to Word document
	 	XWPFDocument doc2 = new XWPFDocument();
		OutputStream os = new FileOutputStream("E:\\Jinal's project\\Udemy\\Java\\WordExample5.docx");
		XWPFParagraph paragraph = doc2.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setText(doc.body().text());
		doc2.write(os); */
	}
	
	public void createPdf() throws IOException, DocumentException, TemplateException {
		//Process Template
		StringWriter stringWriter = new StringWriter();
		template.process(map, stringWriter);
		String html = stringWriter.toString();
		
		//Create PDF document
		com.itextpdf.text.Document document = new com.itextpdf.text.Document();
		FileOutputStream output = new  FileOutputStream(new File("E:\\Jinal's project\\Udemy\\Java\\PDFTest.pdf"));
		PdfWriter writer = PdfWriter.getInstance(document, output);
		document.open();
		
		//Parse HTML and save it to PDF Document
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));
		document.close();
	}
	
	
}
