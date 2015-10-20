package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;


/**
 * Created by laura on 18/01/15.
 */
public class hw2_1 {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("students");
        DBCollection collection = db.getCollection("grades");
        DBCursor cursor = collection.find(new BasicDBObject("type", "homework")).sort(new BasicDBObject("student_id", 1).append("score", -1));


        BasicDBObject obj1;
        BasicDBObject obj2 = new BasicDBObject();

        try {
            while (cursor.hasNext()) {

                obj1 = (BasicDBObject)cursor.next();
                if (cursor.hasNext()){
                    obj2 = (BasicDBObject)cursor.next();
                };

                if (obj1.get("student_id").equals(obj2.get("student_id"))){
                if (obj1.getInt("score") > obj2.getInt("score")) {
                    collection.remove(obj2);
                } else {
                    collection.remove(obj1);
                };
                };

            }
            } finally {
            cursor.close();
        }

            System.out.println(collection.count());

           /* db.grades.find().sort({'score':-1}).skip(100).limit(1)**/

            DBCursor cursor2 = collection.find(new BasicDBObject()).sort(new BasicDBObject("student_id", -1)).skip(100).limit(1);
            System.out.println(cursor2.next());

            /*
            * db.grades.find({},{'student_id':1, 'type':1, 'score':1, '_id':0}).sort({'student_id':1, 'score':1, }).limit(5)
            * */

            DBCursor cursor3 = collection.find(new BasicDBObject(), new BasicDBObject("student_id",true).append("type",true).append("score",true).append("_id",false)).sort(new BasicDBObject("student_id",1).append("score",1)).limit(5);


           try {
            while (cursor3.hasNext()) {
            System.out.println(cursor3.next());
            }
           } finally {
            cursor3.close();
           }

      /*
      * db.grades.aggregate({'$group':{'_id':'$student_id', 'average':{$avg:'$score'}}}, {'$sort':{'average':-1}}, {'$limit':1})
      * */


        // create our pipeline operations, first with the $match
        DBObject match = new BasicDBObject("$match", new BasicDBObject("type", "airfare"));

// build the $projection operation
        DBObject fields = new BasicDBObject("department", 1);
        fields.put("amount", 1);
        fields.put("_id", 0);
        DBObject project = new BasicDBObject("$project", fields );

// Now the $group operation
        DBObject groupFields = new BasicDBObject( "_id", "$department");
        groupFields.put("average", new BasicDBObject( "$avg", "$amount"));
        DBObject group = new BasicDBObject("$group", groupFields);

// Finally the $sort operation
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("amount", -1));

// run aggregation
    /*    List<DBObject> pipeline = Arrays.asList(match, project, group, sort);
        AggregationOutput output = coll.aggregate(pipeline);

       // Let’s take a look at the results of my audit:

        for (DBObject result : output.results()) {
            System.out.println(result);
        }

*/

    }


}
