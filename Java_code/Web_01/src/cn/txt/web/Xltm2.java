package cn.txt.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.xml.sax.Attributes;


public class Xltm2 {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//SAX解析
		//1、获取解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2、从解析工厂获取解析器
		SAXParser parse =factory.newSAXParser();
		//3、编写处理器
		
		//4、加载文档 Document 注册处理器
		PersonPHandler handler=new PersonPHandler();
		//5、解析
		parse.parse(Thread.currentThread().getContextClassLoader()
		.getResourceAsStream("cn/txt/web/p.xml"),handler);
		
		List<Person> persons = handler.getPersons();
		for(Person p:persons) {
			System.out.println(p.getAge()+p.getName());
		}

	}
}

class PersonPHandler extends DefaultHandler{
	private List<Person> persons;
	private Person person;
	private String Tag;
	
	public void startDocument() {
		System.out.println("解析开始-----------");
		persons = new ArrayList<Person>();
	}
	
	
	public PersonPHandler() {
	}
	
	


	public List<Person> getPersons() {
		return persons;
	}



	public void endDocument() {
		System.out.println("解析结束2---------------------");
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		System.out.println(qName+"---1解析开始 ");
		if(null!=qName) {
			Tag = qName; //存储标签名
			if(Tag.equals("person")) {
				person = new Person();
			}
		}
	}
	
	public void  endElement(String uri, String localName, String qName) {
		System.out.println(qName+"---解析结束");
		if(null !=qName) {
			if(qName.equals("person")) {
			persons.add(person);
			}
		}
		Tag  =null;
	}
	
	public void characters(char[] ch, int start, int length) {
		String contents = new String(ch,start,length).trim();
		if(Tag != null) {
			if(Tag.equals("name")) {
				person.setName(contents);
			}else if(Tag.equals("age")) {
				if(contents.length()>0) {
				person.setAge(Integer.valueOf(contents));
				}
			}
		}
		
	}
}