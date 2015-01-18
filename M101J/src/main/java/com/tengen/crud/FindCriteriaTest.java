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

public class FindCriteriaTest {

	public static void main(String[] args) throws UnknownHostException {
		
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("findCriteriaTest");
		collection.drop();
		
		for(int i = 0; i < 10; i++) {
			collection.insert(new BasicDBObject("x", new Random().nextInt(2))
									.append("y", new Random().nextInt(100)));	
		}

		QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(90); 
		DBObject query = new BasicDBObject("x", 0)
						.append("y", new BasicDBObject("$gt", 10).append("$lt", 90));
		
		System.out.println("Count \n");
		System.out.println(collection.count(builder.get()));
		
		System.out.println("Find All \n");
		DBCursor cursor = collection.find(builder.get());
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
