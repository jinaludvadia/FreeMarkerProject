package com.freemarkerdemo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

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
	
	private String html;

	public void createTemplate() throws IOException, TemplateException {
		
		//FreeMarker configuration object
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
		
		// Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(true);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

		//Load template from source folder
		cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\lenovo\\eclipse-workspace\\FreeMarkerProject\\src\\main\\resources\\templates"));

		//Get Template
		Template template = cfg.getTemplate("product.ftl");

		//Create data-model
		Map map = new HashMap();
		map.put("name", "Jinal");

		Product product = new Product("products/watch","Titan Watch",5000);
		map.put("latestProduct", product);
		
		StringWriter stringWriter = new StringWriter();
		template.process(map, stringWriter);
		
		html = stringWriter.toString();
	}
	
	public void createWord() throws IOException {
		//Parse HTML
		Document doc = Jsoup.parse(html);
		
		//Save it to Word document
		XWPFDocument doc2 = new XWPFDocument();
		OutputStream os = new FileOutputStream("E:\\Jinal's project\\Udemy\\Java\\WordExample4.docx");
		XWPFParagraph paragraph = doc2.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setText(doc.body().text());
		doc2.write(os);
	}
	
	public void createPdf() throws IOException, DocumentException {
		//Create PDF document
		com.itextpdf.text.Document document = new com.itextpdf.text.Document();
		FileOutputStream output = new  FileOutputStream(new File("E:\\Jinal's project\\Udemy\\Java\\PDFTest4.pdf"));
		PdfWriter writer = PdfWriter.getInstance(document, output);
		document.open();
		
		//Parse HTML and save it to PDF Document
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));
		document.close();
	}
}
