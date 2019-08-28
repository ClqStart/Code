package cn.txt.web02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
	private List<Entity> entitys = null;
	private List<Mapping> mappings = null;
	//key-->servlet-name  value -->servlet-class
	private Map<String,String> entityMap = new HashMap<String,String>();
	//key -->url-pattern value -->servlet-name
	private Map<String,String> mappingsMap = new HashMap<String,String>();
	
	public WebContext( List<Entity>entitys, List<Mapping> mappings) {
		this.entitys = entitys;
		this.mappings= mappings;
		
		for(Entity entity:entitys) {
			entityMap.put(entity.getName(), entity.getClz());
		}
		for(Mapping m:mappings) {
			for(String patt:m.getPatterns()) {
				mappingsMap.put(patt,m.getName());
			}
		}
	}
	
	public String getClz(String pattern) {
		
		String servletName =mappingsMap.get(pattern);
		System.out.println(servletName);
		System.out.println(entityMap.get(servletName));
		
		return  entityMap.get(servletName);
	}
}
