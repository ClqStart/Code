package cn.txt.web02;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;





public class WebApp {
	private static    WebContext  webcontext;
	static {
			try{//SAX解析
				//1、获取解析工厂
				SAXParserFactory factory=SAXParserFactory.newInstance();
				//2、从解析工厂获取解析器
				SAXParser parse =factory.newSAXParser();
				//3、编写处理器
				
				//4、加载文档 Document 注册处理器
				WebPHandler handler=new WebPHandler();
				//5、解析
				parse.parse(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("cn/txt/web02/web.xml"),handler);
				webcontext = new WebContext( handler.getEntitys(), handler.getMappings());
	}catch(Exception e) {
		System.out.println("配置文件错误");
		}

	}
	public static Servlet getServletFromUrl(String url) {
		
		String className =webcontext.getClz("/"+url);
		Class clz;
		try {
			clz = Class.forName(className);
			Servlet  servlet =(Servlet)clz.getConstructor().newInstance();
			return servlet;
		} catch (Exception e) {
			
			
		}	
		return null;
		
	}
}

