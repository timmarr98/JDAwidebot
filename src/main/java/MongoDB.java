import com.mongodb.*;
import org.bson.types.ObjectId;


import javax.swing.text.Document;
import java.net.UnknownHostException;
public class MongoDB {

    public static MongoClient mongoClient ;
    public static DB database;
    public static DBCollection collection;



//    public static void main(String[] args) throws UnknownHostException {
//
//        mongoClient = new MongoClient();
//        database = mongoClient.getDB("DiscordBot");
//        collection = database.getCollection("users");
//
////        DBObject user = new BasicDBObject("id", "293w9295").append("name", "Joe Biden").append("bio", "whore");
////        collection.insert(user);
//////
////        DBObject query = new BasicDBObject();
////        query.put("id", "2939295");
////
////        DBObject result = collection.findOne(query);
////        System.out.println(result.get("name"));
//////        System.out.println(collection.findOne(new BasicDBObject("id","2939295").size()));
//
//    }

    public void addToDatabase(String userid, String name, String bio) throws UnknownHostException {
        mongoClient = new MongoClient();
        database = mongoClient.getDB("DiscordBot");
        collection = database.getCollection("users");
        DBObject newUser = new BasicDBObject("id", userid).append("name", name).append("bio",bio);
        collection.insert(newUser);
        System.out.println("Transfer Done");
    }

}
