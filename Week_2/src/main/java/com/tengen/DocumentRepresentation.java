package com.tengen;

import com.mongodb.BasicDBObject;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by laura on 17/01/15.
 */
public class DocumentRepresentation {

    public static void main(String[] args) {
        BasicDBObject doc = new BasicDBObject();
        doc.put("username","jyemin");
        doc.put("birthdate", new Date(234832423));
        doc.put("programmer",true);
        doc.put("age",8);
        doc.put("languages", Arrays.asList("Java", "C++"));
        doc.put("address", new BasicDBObject("street","12 ranelagh").append("zip","56789").append("town","westfield"));


    }
}
