package com.tengen;

import java.io.StringWriter;
import java.net.UnknownHostException;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreeMarkerStyle {

	public static void main(String[] args) throws UnknownHostException {

		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreeMarkerStyle.class, "/");
		
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		DB db = client.getDB("course");
		final DBCollection collection = db.getCollection("hello");

		Spark.get("/", new Route() {
			
			public Object handle(Request arg0, Response arg1) throws Exception {
				StringWriter writer = new StringWriter();
				try {
					Template template = configuration.getTemplate("hello.ftl");
					DBObject doc = collection.findOne();
					
					template.process(doc, writer);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return writer;
			}
		});
	}

}
