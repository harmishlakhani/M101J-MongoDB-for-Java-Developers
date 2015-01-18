package com.tengen.crud;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

public class DotNotationTest {

	public static void main(String[] args) throws UnknownHostException {
		
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("dotNotationTest");
		collection.drop();
		Random rand = new Random();
		
		for(int i = 0; i < 10; i++) {
			collection.insert(new BasicDBObject("_id", i)
					.append("start", new BasicDBObject("x", rand.nextInt(90) + 10)
												.append("y", rand.nextInt(90) + 10))
					.append("end", new BasicDBObject("x", rand.nextInt(90) + 10)
												.append("y", rand.nextInt(90) + 10))
					
					);	
		}

		DBObject query = QueryBuilder.start("start.x").greaterThan(50).get();
		
		System.out.println("Find All \n");
		DBCursor cursor = collection.find(query);
		try {
			while(cursor.hasNext()) {
				DBObject obj = cursor.next();
				System.out.println(obj);
			}
		} finally {
			cursor.close();
		}
		
		
	}

}
