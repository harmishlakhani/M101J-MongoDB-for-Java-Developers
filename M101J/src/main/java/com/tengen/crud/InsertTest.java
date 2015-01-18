package com.tengen.crud;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class InsertTest {

	public static void main(String[] args) throws UnknownHostException {

		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("insertTest");
		
		DBObject doc = new BasicDBObject().append("x", 1);
		System.out.println(doc);
		
		collection.insert(doc);
		doc.removeField("_id");
		collection.insert(doc);
		System.out.println(doc);
	}

}
