package com.tengen.crud;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class UpdateTest {

	public static void main(String[] args) throws UnknownHostException {
		
		DBCollection collection = createCollection();
		List<String>  names = Arrays.asList("hcl", "bapu", "robin", "mayur");
		for(String name  : names)
			collection.insert(new BasicDBObject("_id", name));

		collection.update(new BasicDBObject("_id", "hcl"), new BasicDBObject("age", 24));
		collection.update(new BasicDBObject("_id", "hcl"), 
				new BasicDBObject("$set", new BasicDBObject("gender", "M")));
		
		//Upsert is true
		collection.update(new BasicDBObject("_id", "prashant"), 
				new BasicDBObject("$set", new BasicDBObject("gender", "M")), true, false);
		
		//Multi is true
		collection.update(new BasicDBObject(), 
				new BasicDBObject("$set", new BasicDBObject("title", "Dr")), false, true);
		
		collection.remove(new BasicDBObject("_id", "bapu"));
		
		printCollection(collection);
	}

	private static void printCollection(DBCollection collection) {
		DBCursor cursor = collection.find();
		try {
			while(cursor.hasNext()) {
				DBObject obj = cursor.next();
				System.out.println(obj);
			}
		} finally {
			cursor.close();
		}
	}

	private static DBCollection createCollection() throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("updateTest");
		collection.drop();
		return collection;
	}

}
