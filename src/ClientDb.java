
import java.net.UnknownHostException;

import org.bson.BasicBSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class ClientDb {

	private static DB db = null;
    private static MongoClient mongo = null;
    
    public static void init() {
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (final UnknownHostException e) {
        	e.printStackTrace();
        }
        db = mongo.getDB("cmpe273Client");
    }
    
    public static void close(){
    	mongo.close();
    }
    
    public static void insert(String manufacturer, String serialNumber, int lightWatts, String lightColor, String registerURI){
    	String data = "{\"Manufacturer\": \""+manufacturer+"\", \"SerialNumber\": \""+serialNumber+"\", "
    			+ "\"lightWatts\": \""+lightWatts+"\", \"lightColor\": \""+lightColor+"\", "
    			+ "\"registerURI\": \""+registerURI+"\"}";
    	if(db == null){
    		init();
    	}
        DBCollection collection = db.getCollection("clientInfo");
    	DBObject object = (DBObject)JSON.parse(data);
    	collection.insert(object);
    }
    
    public static String search(String item){
    	String found = "";
    	if (db == null)
    		init();
    	
    	DBCollection collection = db.getCollection("clientInfo");
    	DBObject object = collection.findOne();
    	found = ((BasicBSONObject) object).getString(item);
    	
    	return found;
    }
    
    public static void delete(){
    	if (db == null)
    		init();
    	
        DBCollection collection = db.getCollection("clientInfo");
        DBObject object = collection.find().one();
        collection.remove(object);
    }
    
}