package com.tengen.crud;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class FindAndModifyTest {

	public static void main(String[] args) throws UnknownHostException {
		
		DBCollection collection = createCollection();
		
		final String counterId = "abc";
		int first;
		int numNeeded;
		
		numNeeded = 2;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range:" + first + "-" + (first + numNeeded - 1));
		
		numNeeded = 3;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range:" + first + "-" + (first + numNeeded - 1));
		
		numNeeded = 10;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range:" + first + "-" + (first + numNeeded - 1));
		
		printCollection(collection);
	}

	private static int getRange(String counterId, int numNeeded,
			DBCollection collection) {

		DBObject doc = collection.findAndModify(
				new BasicDBObject("_id", counterId), null, null, false, 
				new  BasicDBObject("$inc", new BasicDBObject("counter", numNeeded)),
				true, true);
		return (Integer) doc.get("counter") - numNeeded + 1;
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
		DBCollection collection = db.getCollection("findAndModifyTest");
		collection.drop();
		return collection;
	}

}
