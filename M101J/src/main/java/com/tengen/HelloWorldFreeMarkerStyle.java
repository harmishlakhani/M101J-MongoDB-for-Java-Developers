package com.tengen;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldFreeMarkerStyle {

	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");
		
		try {
			Template template = configuration.getTemplate("hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String,  Object> map = new HashMap<String , Object>();
			map.put("name", "Harmish");
			
			template.process(map, writer);
			System.out.println(writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
