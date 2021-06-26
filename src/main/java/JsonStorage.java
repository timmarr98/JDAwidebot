import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
//import

    public class JsonStorage {
        JSONArray jsonArray = new JSONArray();

//        public void insertUserInfo(String usersID)
//        {
//
//
//            JSONObject obj = new JSONObject();
//            JSONObject objItem = new JSONObject();
//            objItem.put("Bio", "somethingsd sd " );
//            obj.put(usersID, objItem);
////            jsonArray.add(obj);
//
//            try(FileWriter file = new FileWriter("src/main/java/storage.json",false)){
////                file.write("{");
//                file.write(obj.toString());
////                file.write("}");
//            }
//            {
//            catch (Exception err)
//                System.out.print(err);
//            }
//
//
//
//
//        }
        private JSONObject createUserProfile(String userID)
        {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("name","fartypants");
            jsonMessage.put("bio", "i stink");

            return jsonMessage;



        }
        JSONObject obj = new JSONObject();

        public void addProfile(String userID)
        {
            try {
                obj.put(userID,createUserProfile(userID));
            }
            catch(Exception err)
            {
                System.out.println(err);
            }

        }

//        public void saveProfile(String userId)
//        {
//            try
//            {
//                addProfile()
//            }
//        }
    public static void main(String[] args)
    {
        JsonStorage test = new JsonStorage();
        test.addProfile("fart");
    }



}