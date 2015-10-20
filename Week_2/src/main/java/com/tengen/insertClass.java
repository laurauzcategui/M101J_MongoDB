package com.tengen;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by laura on 17/01/15.
 */
public class insertClass {

    public static void main(String[] args)throws UnknownHostException{
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("myInsert");

        BasicDBObject doc = new BasicDBObject().append("x",1);

        System.out.println(doc);
        collection.insert(doc);
        System.out.println(doc);

        collection.findOne();
    }
}
