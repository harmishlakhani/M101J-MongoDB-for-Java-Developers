package com.tengen.crud.hw;

import java.net.UnknownHostException;
import java.util.Iterator;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class HW31 {

	public static void main(String[] args) throws UnknownHostException {
		
		DBCollection collection = createCollection();
		DBCursor cursor = collection.find(new BasicDBObject());
		
		for(int i = 0; i < 200; i++) {
			DBObject obj = cursor.next();
			BasicDBList scores = (BasicDBList) obj.get("scores");
			BasicDBObject lowScores = null;
			Double score = Double.MAX_VALUE;
			for(Object object: scores) {
				BasicDBObject basicDBObject = (BasicDBObject) object;
				if(basicDBObject.get("type").equals("homework")) {
					if (score.compareTo((Double) basicDBObject.get("score")) > 0) {
						score = (Double) basicDBObject.get("score");
						lowScores = basicDBObject;
					}
				}
			}
			
			System.out.println(lowScores);
			scores.remove(lowScores);
			collection.update(new BasicDBObject("_id", obj.get("_id")), obj, true, false);
		}
		//printCollection(collection);
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
		DB db = client.getDB("school");
		DBCollection collection = db.getCollection("students");
		return collection;
	}

}
