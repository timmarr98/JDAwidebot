import com.mongodb.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
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
     * @return True if the user exists in the data, else false.
     * @throws UnknownHostException Typically not found
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
     * @param userid Discord's designated UNIQUE ID
     * @return userStorage (a String array)
     */
    public String [] userInfo(String userid)
    {
        String[] userStorage = new String[3];

        DBObject query = new BasicDBObject();
        query.put("_id", userid);
        DBObject result = collection.findOne(query);
        userStorage[0] =  result.get("name").toString();
        userStorage[1] = result.get("bio").toString();
        userStorage[2] = result.get("age").toString();
        return userStorage;
    }

    /**
     * Adds the user to database
     * @param userid The Discord's ID for the user
     * @param name  The name of the user
     * @param bio The bio for the user
     * @param age The age of the user
     * @throws UnknownHostException if
     */
    public void addToDatabase(String userid, String name, String bio, String age) throws UnknownHostException {
        DBObject newUser = new BasicDBObject("_id", userid).append("name", name).append("bio",bio).append("age", age);
        collection.insert(newUser);
        System.out.println("Transfer Done");
    }

    public void bio(String userid, String userbio)
    {
        DBObject query = new BasicDBObject();
        query.put("_id", userid);
        DBObject result = collection.findOne(query);
        database.getCollection("users").update(result, new BasicDBObject("$set", new BasicDBObject("bio",userbio)));
        System.out.println("Database bio updated");
    }

    public void age(String userid, String age)
    {
        DBObject query = new BasicDBObject();
        query.put("_id", userid);
        DBObject result = collection.findOne(query);
        database.getCollection("users").update(result, new BasicDBObject("$set", new BasicDBObject("age",age)));
        System.out.println("Database bio updated");
    }

    public void name(String userid, String name)
    {
        DBObject query = new BasicDBObject();
        query.put("_id", userid);
        DBObject result = collection.findOne(query);
        database.getCollection("users").update(result, new BasicDBObject("$set", new BasicDBObject("name",name)));
        System.out.println("Database bio updated");
    }

}
