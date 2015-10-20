package com.tengen;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.io.IOException;

public class Question8
{
    public static void main(String[] args) throws IOException
    {



        MongoClient c  = new MongoClient();
        DB db = c.getDB("zoo");
        DBCollection animals = db.getCollection("animals");

        BasicDBObject animal = new BasicDBObject("animal", "monkey");

        animals.insert(animal);
        animal.removeField("animal");
        animal.append("animal", "cat");
        animals.insert(animal);
        animal.removeField("animal");
        animal.append("animal", "lion");
        animals.insert(animal);
    }
}