package cn.txt.web;

import java.io.IOException;
import java.util.jar.Attributes;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;








public class Xltm01 {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//SAX解析
		//1、获取解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2、从解析工厂获取解析器
		SAXParser parse =factory.newSAXParser();
		//3、编写处理器
		
		//4、加载文档 Document 注册处理器
		PHandler handler=new PHandler();
		//5、解析
		parse.parse(Thread.currentThread().getContextClassLoader()
		.getResourceAsStream("cn/txt/web/p.xml"),handler);

	}
}

class PHandler extends DefaultHandler{
	public void startDocument() {
		System.out.println("解析开始2-----------");
	}
	
	
	public void endDocument() {
		System.out.println("解析结束2---------------------");
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		System.out.println(qName+"---1解析开始 ");
		
	}
	
	public void  endElement(String uri, String localName, String qName) {
		System.out.println(qName+"---1解析结束");
	}
	
	public void characters(char[] ch, int start, int length) {
		String contents = new String(ch,start,length).trim();
		
		if(contents.length() >0) {
			System.out.println("内容为"+contents);
		}else {
			
		System.out.println("内容为"+"空");
		
		}
	}
}