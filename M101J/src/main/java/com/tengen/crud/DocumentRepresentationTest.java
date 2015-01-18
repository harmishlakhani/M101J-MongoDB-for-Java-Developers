package com.tengen.crud;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class DocumentRepresentationTest {

	public static void main(String[] args) {

		BasicDBObject doc = new BasicDBObject();
		doc.put("userName", "hcl");
		doc.put("age", 25);
		doc.put("birthdate", new Date());
		doc.put("programmer", true);
		doc.put("languages", Arrays.asList("Java", "C++"));
		doc.put("address", new BasicDBObject("street", "patoliya street")
							.append("city", "dhoraji")
							.append("zip", 360410));
	}

}
