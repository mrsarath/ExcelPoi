package com.sarath.mytestproj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        String rawHtml = "<p>this is para <b>bold text</p>";
        
        Tidy tidy = new Tidy();
        tidy.setPrintBodyOnly(true);

        //parse(tidy, rawHtml);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        ByteArrayInputStream inputStream;
        
        Document doc = null;
		try {
			inputStream = new ByteArrayInputStream(rawHtml.getBytes("UTF-8"));
			doc = tidy.parseDOM(inputStream, ps);
			tidy.pprint(doc, ps);
			
			System.out.println( baos.toString("UTF8") );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
		
		
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();
		String expression = "//*[text()!='']/.";
		XPathExpression xPathExpression;
		try {
			xPathExpression = xPath.compile(expression);
			Object result = xPathExpression.evaluate(doc,XPathConstants.NODESET);
			System.out.println(result.toString());
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         

    }
    
    public static void parse(Tidy tidy, String rawHtml ) {
    	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PrintStream ps = new PrintStream(baos);
         
    	 tidy.parse(new StringReader(rawHtml), ps);
         try {
 			System.out.println( baos.toString("UTF8") );
 		} catch (UnsupportedEncodingException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
    }
}
