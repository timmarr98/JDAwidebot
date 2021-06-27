import com.mongodb.*;
import org.bson.types.ObjectId;


import javax.swing.text.Document;
import java.net.UnknownHostException;
public class MongoDB {

    public static MongoClient mongoClient;

    static {
        try {
            mongoClient = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    ;
    public static DB database = mongoClient.getDB("DiscordBot");
    public static DBCollection collection = database.getCollection("users");;



//    public static void main(String[] args) throws UnknownHostException {
//
////        mongoClient = new MongoClient();
////        database = mongoClient.getDB("DiscordBot");
////        collection = database.getCollection("users");
////
//////        DBObject user = new BasicDBObject("id", "293w9295").append("name", "Joe Biden").append("bio", "whore");
//////        collection.insert(user);
////////
////        DBObject query = new BasicDBObject();
////        query.put("_id", "crap");
//////
////        DBObject result = collection.findOne(query);
////        System.out.println(result.get("name"));
////////        System.out.println(collection.findOne(new BasicDBObject("id","2939295").size()));
//
//    }

    public boolean doesExist(String userId) throws UnknownHostException {

        DBObject query = new BasicDBObject();
        query.put("_id", userId);
        DBObject result = collection.findOne(query);
        try {
//            System.out.println(result.get("name"));
            result.get("name");
        }
        catch(NullPointerException e)
        {
            return false;
        }
        return true;
    }
    public void addToDatabase(String userid, String name, String bio, String age) throws UnknownHostException {
        mongoClient = new MongoClient();
        database = mongoClient.getDB("DiscordBot");
        collection = database.getCollection("users");
        DBObject newUser = new BasicDBObject("_id", userid).append("name", name).append("bio",bio).append("age", age);
        collection.insert(newUser);
        System.out.println("Transfer Done");
    }

}
