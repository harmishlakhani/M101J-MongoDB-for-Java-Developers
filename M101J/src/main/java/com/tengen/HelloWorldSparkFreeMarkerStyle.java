package com.tengen;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldSparkFreeMarkerStyle {

	public static void main(String[] args) {

		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreeMarkerStyle.class, "/");
		
		Spark.get("/", new Route() {
			
			public Object handle(Request arg0, Response arg1) throws Exception {
				StringWriter writer = new StringWriter();
				try {
					Template template = configuration.getTemplate("hello.ftl");
					
					Map<String,  Object> map = new HashMap<String , Object>();
					map.put("name", "Harmish");
					
					template.process(map, writer);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return writer;
			}
		});
	}

}
