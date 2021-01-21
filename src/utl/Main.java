package utl;

//import org.json.simple.parser.JSONParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
        import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import org.jsoup.Jsoup;


import javax.management.Query;
import javax.print.Doc;
import java.awt.geom.Arc2D;
import java.io.IOException;
        import java.util.*;
//import org.json.simple.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {


        String uri = "";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDBDad");
        MongoCollection collection = mongoDatabase.getCollection("info");
        System.out.println("DB connected");


        // write your code here

        org.jsoup.nodes.Document doc = Jsoup.connect("https://www.pik.ru/gp/search/chessplan?bulk_id=4150").userAgent("Chrome/4.0.249.0 Safari/532.5").maxBodySize(Integer.MAX_VALUE).get();
        String str = doc.toString();
        //System.out.println(str);
        char[] charstr = str.toCharArray();

        String findStr = "{\"id\":";
        int lastIndex = 0;
        List<String> result = new ArrayList<String>();

        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {


                String id=( (String.valueOf(charstr[lastIndex + 6]))+(String.valueOf(charstr[lastIndex + 7]))+(String.valueOf(charstr[lastIndex + 8]))+(String.valueOf(charstr[lastIndex + 9]))+(String.valueOf(charstr[lastIndex + 10]))+(String.valueOf(charstr[lastIndex + 11])));
                Document document = new Document("id", id);
                Document found = (Document) collection.find (document).first();
                if (isNumeric(id)){
                    try {

                        String price = "";

                        org.jsoup.nodes.Document newdoc = Jsoup.connect("https://api.pik.ru/v1/flat?id=" + id + "&similar=0").userAgent("Chrome/4.0.249.0 Safari/532.5").ignoreContentType(true).maxBodySize(Integer.MAX_VALUE).get();
                        String newstr = newdoc.toString();
                        char[] newcharstr = newstr.toCharArray();

                        int newlastIndex = 0;
                        String newfindStr = "\"price\":";
                        try {
                            while (newlastIndex != -1) {
                                newlastIndex = newstr.indexOf(newfindStr, newlastIndex);

                                if (newlastIndex != -1) {
                                    int i = newlastIndex + 8;
                                    price = "";
                                    while (isNumeric(String.valueOf(newcharstr[i]))) {
                                        price += String.valueOf(newcharstr[i]);
                                        i++;

                                    }


                                    newlastIndex += 1;

                                }
                            }

                        } catch (Exception e) {
                            price = "null";
                        }


                        newlastIndex = 0;
                        newfindStr = "\"rooms\":";
                        String rooms = "";
                        try {
                            while (newlastIndex != -1) {
                                newlastIndex = newstr.indexOf(newfindStr, newlastIndex);

                                if (newlastIndex != -1) {
                                    int i = newlastIndex + 9;
                                    rooms = "";
                                    while (isNumeric(String.valueOf(newcharstr[i]))||Character.isLetter(newcharstr[i])) {
                                        rooms += String.valueOf(newcharstr[i]);
                                        i++;

                                    }


                                    newlastIndex += 1;

                                }
                            }

                        } catch (Exception e) {
                            rooms = "null";
                        }
                        newlastIndex = 0;
                        newfindStr = "\"floor\":";
                        String floor = "";
                        try {
                            while (newlastIndex != -1) {
                                newlastIndex = newstr.indexOf(newfindStr, newlastIndex);

                                if (newlastIndex != -1) {
                                    int i = newlastIndex + 8;
                                    floor = "";
                                    while (isNumeric(String.valueOf(newcharstr[i]))) {
                                        floor += String.valueOf(newcharstr[i]);
                                        i++;

                                    }


                                    newlastIndex += 1;

                                }
                            }

                        } catch (Exception e) {
                            floor = "null";
                        }

                        newlastIndex = 0;
                        newfindStr = "\"discount\":";
                        String discount = "";
                        try {
                            while (newlastIndex != -1) {
                                newlastIndex = newstr.indexOf(newfindStr, newlastIndex);

                                if (newlastIndex != -1) {
                                    int i = newlastIndex + 11;
                                    discount = "";
                                    while (isNumeric(String.valueOf(newcharstr[i]))) {
                                        discount += String.valueOf(newcharstr[i]);
                                        i++;

                                    }


                                    newlastIndex += 1;

                                }
                            }

                        } catch (Exception e) {
                            discount = "null";
                        }
                        newlastIndex = 0;
                        newfindStr = "\"area\":";
                        String area = "";
                        try {
                            while (newlastIndex != -1) {
                                newlastIndex = newstr.indexOf(newfindStr, newlastIndex);

                                if (newlastIndex != -1) {
                                    int i = newlastIndex + 7;
                                    area = "";
                                    while (isNumeric(String.valueOf(newcharstr[i])) || newcharstr[i] == '.') {
                                        area += String.valueOf(newcharstr[i]);
                                        i++;

                                    }


                                    newlastIndex += 1;

                                }
                            }

                        } catch (Exception e) {
                            area = "null";
                        }
                        newlastIndex = 0;
                        newfindStr = "\"status\":";
                        String status = "";
                        try {
                            while (newlastIndex != -1) {
                                newlastIndex = newstr.indexOf(newfindStr, newlastIndex);

                                if (newlastIndex != -1) {
                                    int i = newlastIndex + 10;
                                    status = "";
                                    while (Character.isLetter(newcharstr[i])) {
                                        status += String.valueOf(newcharstr[i]);
                                        i++;

                                    }


                                    newlastIndex += 1;

                                }
                            }

                        } catch (Exception e) {
                            status = "null";
                        }


                        System.out.println("id " + id);
                        System.out.println("rooms " + rooms);
                        System.out.println("price " + price);
                        System.out.println("floor " + floor);
                        System.out.println("discount " + discount);
                        System.out.println("area " + area);
                        System.out.println("status " + status);

                        if (found == null) {
                            Date date = new Date();
                            Document documents = new Document("id", id);
                            documents.append("rooms", rooms).append("floor", floor).append("area", area).append("status", status).append("discount", discount);
                            documents.append("cost", new Document(date.toString(), Integer.parseInt(price) * (100 - (Double.parseDouble(discount))) / 100));
                            collection.insertOne(documents);

//                    Document testObject = new Document("id", id);
//                    collection.insertOne(testObject);
//                    Document newAddress = new Document().append(date.toString(),price);
//
//                    collection.updateOne(testObject, Updates.addToSet("cost",newAddress));


//                    ArrayList<Document> milestones = new ArrayList<>();
//                    milestones.add(new Document(date.toString(),price));
//                    milestones.add(new Document(date.toString(),price));
//                    milestones.add(new Document(date.toString(),price));
//                    testObject.put("cost", milestones);
//                    collection.insertOne(testObject);


                        } else {
                            Document query = new Document("id", id);
                            Date date = new Date();
//                        Bson upd = new Document("corner", "#15 2Easy Street");
//                        Bson u = new Document("$set", new Document("cost", new BasicDBObject("corner", "01000")));
//                        collection.updateOne(found, u);
//
//                        BasicDBObject updateFields = new BasicDBObject();
//                        updateFields.append("cost", new BasicDBObject("corner", "01000"));
//                        BasicDBObject searchQuery = new BasicDBObject();
//                        searchQuery.append("id", id);

//                        ArrayList<Document> docList = new ArrayList<Document>();
//                        docList.add(new Document().append("n0", 102));
//                        Document push = new  Document().append("$push", new Document().append("cost", new Document().append("$each", docList).append("$position", 0)));
//                        collection.updateMany(found, push);


                            Document comments = null;


                            comments = (Document) found.get("cost");

                            System.out.println(comments);
                            comments.append(date.toString(), Integer.parseInt(price) * (100 - (Double.parseDouble(discount))) / 100);


                            System.out.println(comments);
                            collection.updateOne(new Document("id", id), new Document("$set", new Document("cost", comments)));
                            comments = new Document().append("rooms", rooms).append("floor", floor).append("area", area).append("status", status).append("discount", discount);
                            collection.updateOne(new Document("id", id), new Document("$set", comments));

//                        BasicDBObject modifiedObject =new BasicDBObject();
//                        modifiedObject.put("$set", new BasicDBObject().append("cost", new BasicDBObject("Expert One-on-One J2EE Development without EJB","jj")));
//                        collection.updateOne(found, modifiedObject);

//                        Document queryDocument = new Document("id", id).append("cost", new Document());
//                        Document sourceDocument = (Document) collection.find(queryDocument).first();
//                        Document elementToArray = new Document("cost", "three");
//                        Document pushElement = new Document("$push", elementToArray);
//                        collection.updateMany(queryDocument,pushElement);
                        }


                        result.add(id + "--" + price);
                    } catch (Exception e){}
                }
                lastIndex += 1;
            }
        }
        result.forEach(System.out::println);









//        result.forEach(s ->{
//            try {
//                Document newdoc = Jsoup.connect("https://api.pik.ru/v1/flat?id=" + s + "&similar=0").maxBodySize(Integer.MAX_VALUE).get();
//                String newstr = newdoc.toString();
//                int newlastIndex = 0;
//                String newfindStr ="";
//                while (newlastIndex != -1) {
//
//                    newlastIndex = str.indexOf(newfindStr, newlastIndex);
//
//                    if (newlastIndex != -1) {
//                        price.add(newlastIndex);
//                        newlastIndex += 1;
//                    }
//
//
//                }
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } );





    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}