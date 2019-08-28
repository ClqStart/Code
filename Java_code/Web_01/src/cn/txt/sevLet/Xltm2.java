package cn.txt.sevLet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;




public class Xltm2 {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		//SAX解析
		//1、获取解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2、从解析工厂获取解析器
		SAXParser parse =factory.newSAXParser();
		//3、编写处理器
		
		//4、加载文档 Document 注册处理器
		WebPHandler handler=new WebPHandler();
		//5、解析
		parse.parse(Thread.currentThread().getContextClassLoader()
		.getResourceAsStream("cn/txt/sevLet/web.xml"),handler);
		
		WebContext  context = new WebContext( handler.getEntitys(), handler.getMappings());
		String className =context.getClz("/g");
		Class clz = Class.forName(className);
		
		Servlet  servlet =(Servlet)clz.getConstructor().newInstance();
		System.out.println(servlet);
		servlet.service();
		
		
	}
}

class WebPHandler extends DefaultHandler{
	private List<Entity> entitys;
	private List<Mapping> mappings;
	
	private Entity entity;
	private Mapping mapping;
	private String Tag;
	private boolean isMapping =false;//操作谁
	
	public void startDocument() {
		System.out.println("解析开始-----------");
		 entitys= new ArrayList<Entity>();
		 mappings = new ArrayList<Mapping>();
	}
	
	
	public WebPHandler() {
	}
	


	public void endDocument() {
		System.out.println("解析结束2---------------------");
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		System.out.println(qName+"---1解析开始 ");
		if(null!=qName) {
			Tag = qName; //存储标签名
			if(Tag.equals("servlet")) {
				entity = new Entity();
				isMapping =false;
			}else if(Tag.equals("servlet-mapping")) {
				mapping = new Mapping();
				isMapping =true;
			}
		}
	}
	
	public void  endElement(String uri, String localName, String qName) {
		System.out.println(qName+"---解析结束");
		if(null !=qName) {
			if(qName.equals("servlet")) {
			entitys.add(entity);
			}else if(qName.equals("servlet-mapping")) {
				mappings.add(mapping);
			}
		}
		Tag  =null;
	}
	
	public void characters(char[] ch, int start, int length) {
		String contents = new String(ch,start,length).trim();
		if(Tag != null) {
			if(isMapping) {
				if(Tag.equals("servlet-name")) {
					mapping.setName(contents);
				}else if(Tag.equals("url-pattern")) {
					mapping.addPattern(contents);
				}
			}else {
				if(Tag.equals("servlet-name")) {
					entity.setName(contents);
				}else if(Tag.equals("servlet-class")) {
					entity.setClz(contents);;
				}
			}
			
		}
		
	}


	public List<Entity> getEntitys() {
		return entitys;
	}


	public void setEntitys(List<Entity> entitys) {
		this.entitys = entitys;
	}


	public List<Mapping> getMappings() {
		return mappings;
	}


	public void setMappings(List<Mapping> mappings) {
		this.mappings = mappings;
	}
}