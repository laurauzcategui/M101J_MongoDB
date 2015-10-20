package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by laura on 24/01/15.
 */

public class HW3_1 {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("school");
        DBCollection collection = db.getCollection("students");

    /*
    * cur=db.students.aggregate(
    * {"$unwind":"$scores"},
    * {"$match":{"scores.type":"homework"}},
    * {"$sort":{"_id":1,"scores.score":1}},
    * {"$group":{"_id":"$_id","minimum":{"$min":"$scores.score"}}});
    * */
   /*
 MONGO SHELL : db.aggregationExample.aggregate(
 {$match : {type : "local"}} ,
 {$project : { department : 1 , amount : 1 }} ,
 {$group : {_id : "$department" , average : {$avg : "$amount"} } } ,
 {$sort : {"amount" : 1}}
 );
 */
        DBObject match = new BasicDBObject("$match", new BasicDBObject("scores.type", "homework"));

        DBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$_id")
                .append("min", new BasicDBObject("$min", "$scores.score")));
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id", 1).append("scores.score",1));

        DBObject unwind = new BasicDBObject("$unwind", "$scores");


        AggregationOutput output = collection.aggregate(unwind,match,sort,group);

        for (DBObject result : output.results()) {
            //collection.remove(new BasicDBObject("scores.type","homework").append("scores.score", result.get("min")));


         //  System.out.println(result.get("min"));


            /*
            *   DBCursor cursor =  collection.find(new BasicDBObject("scores.type", "homework")
                      .append("_id", result.get("_id"))
                      .append("scores.score", result.get("min")),
                      new BasicDBObject("scores",true).append("_id",false));
           */


            DBObject query = new BasicDBObject("scores.type", "homework").append("_id", result.get("_id"));


            DBObject statusQuery = new BasicDBObject("score", result.get("min"));
            DBObject elemMatchQuery = new BasicDBObject("$elemMatch", statusQuery);

            DBObject fields = new BasicDBObject();
            fields.put("scores", elemMatchQuery);
            fields.put("_id", 0);

            DBCursor cursor = collection.find(query, fields);

            while (cursor.hasNext()) {

                // DBObject obj = cursor.next();


                BasicDBObject obj = ( BasicDBObject ) cursor.next();
                System.out.println(obj);
                BasicDBList score = ( BasicDBList ) obj.get( "scores" );
                System.out.println(score);
                for( Iterator< Object > it = score.iterator(); it.hasNext(); )
                {
                    BasicDBObject obj2     = ( BasicDBObject ) it.next();
                    System.out.println(obj2);
                    collection.update(new BasicDBObject("_id",result.get("_id")), new BasicDBObject("$pull",new BasicDBObject("scores",obj2)));

                }

            }



        }



    }


}


