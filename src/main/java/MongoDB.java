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


    public static DB database = mongoClient.getDB("DiscordBot");
    public static DBCollection collection = database.getCollection("users");;


    /**
     * Checks to see if the user is already found in the database by checking a necessary field
     * @param userId
     * @return
     * @throws UnknownHostException
     */
    public boolean doesExist(String userId) throws UnknownHostException {

        DBObject query = new BasicDBObject();
        query.put("_id", userId);
        DBObject result = collection.findOne(query);
        try {
            result.get("name");
        }
        catch(NullPointerException e)
        {
            return false;
        }
        return true;
    }

    /**
     * Gathers the userinfo from the MongoDB database
     * @param userid
     * @return userStorage (a String array)
     */
    public String [] userInfo(String userid)
    {
        String[] userStorage = new String[3];

        DBObject query = new BasicDBObject();
        query.put("_id", userid);
        DBObject result = collection.findOne(query);
        System.out.println(result.get("name").toString());
        userStorage[0] =  result.get("name").toString();
        System.out.println(userStorage[0]);
        userStorage[1] = result.get("bio").toString();
        System.out.println(userStorage[1]);
        userStorage[2] = result.get("age").toString();
        System.out.println(userStorage[2]);
        return userStorage;
    }

    /**
     * Adds the user to database
     * @param userid
     * @param name
     * @param bio
     * @param age
     * @throws UnknownHostException
     */
    public void addToDatabase(String userid, String name, String bio, String age) throws UnknownHostException {
        mongoClient = new MongoClient();
        database = mongoClient.getDB("DiscordBot");
        collection = database.getCollection("users");
        DBObject newUser = new BasicDBObject("_id", userid).append("name", name).append("bio",bio).append("age", age);
        collection.insert(newUser);
        System.out.println("Transfer Done");
    }
//    public static void main(String[] args) throws UnknownHostException {
////
//////        mongoClient = new MongoClient();
//////        database = mongoClient.getDB("DiscordBot");
//////        collection = database.getCollection("users");
//////
////////        DBObject user = new BasicDBObject("id", "293w9295").append("name", "Joe Biden").append("bio", "whore");
////////        collection.insert(user);
//////////
//            DBObject query = new BasicDBObject();
//            query.put("_id", "192890154237689856");
//            DBObject result = collection.findOne(query);
//            System.out.println(result.get("name").toString() + " from mongo DB");
//////////        System.out.println(collection.findOne(new BasicDBObject("id","2939295").size()));
////
//    }

}
