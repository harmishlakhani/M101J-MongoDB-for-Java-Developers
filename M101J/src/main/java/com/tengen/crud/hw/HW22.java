package com.tengen.crud.hw;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class HW22 {

	public static void main(String[] args) throws UnknownHostException {
		
		DBCollection collection = createCollection();
		List<DBObject> list = new ArrayList<DBObject>();
		for(int i = 0; i < 200; i++) {
			DBCursor cursor = collection.find(new BasicDBObject("type", "homework").append("student_id", i)).sort(new BasicDBObject("score", 1));
			DBObject obj = cursor.next();
			list.add(obj);
		}
		for(DBObject id : list) {
			collection.remove(id);
			//System.out.println(id.toString());
		}
		System.out.println(collection.count());
		//printCollection(collection);
	}

	private static void printCollection(DBCollection collection) {
		//DBCursor cursor = collection.find();
		List<ObjectId> list = new ArrayList<ObjectId>();
		
		DBCursor cursor = collection.find(new BasicDBObject("type", "homework")).sort(new BasicDBObject("student_id", 1).append("score", 1));
		try {
			DBObject temp = cursor.next();
			list.add((ObjectId) temp.get("_id"));
			
			while(cursor.hasNext()) {
				DBObject obj = cursor.next();
				if((Integer) temp.get("student_id") != (Integer) obj.get("student_id")) {
					//collection.remove(temp);
					list.add((ObjectId) temp.get("_id"));
					temp = obj;
				}
				System.out.println(obj);
			}
		} finally {
			cursor.close();
		}
		
		for(ObjectId id : list)
			System.out.println(id.toString());
	}

	private static DBCollection createCollection() throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		DB db = client.getDB("students");
		DBCollection collection = db.getCollection("grades");
		return collection;
	}

}
