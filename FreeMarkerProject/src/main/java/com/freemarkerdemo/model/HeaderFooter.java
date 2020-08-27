package com.freemarkerdemo.model;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HeaderFooter extends PdfPageEventHelper{
	
	public static final String HEADER =
		    "<table border=\"1\" cellspacing=\"1\" cellpadding=\"1\" width=\"50%\"><tr><th><b>Name</b></th><th><b>Place</b></th></tr></table>";
	
	protected ElementList header;
	
    public HeaderFooter() throws IOException {
        header = XMLWorkerHelper.parseToElementList(HEADER, null);
        }
    
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
    	if(document.getPageNumber()!=1) {
	        try {
	            ColumnText ct = new ColumnText(writer.getDirectContent());
	            ct.setSimpleColumn(new Rectangle(36, 832, 559, 810));
	            for (Element e : header) {
	                ct.addElement(e);
	            }
	            ct.go();
	        } catch (DocumentException de) {
	            throw new ExceptionConverter(de);
	        }
    	}
    }
}
