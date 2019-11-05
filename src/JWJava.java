/*
 * Test Program for JWApi
 */
package jwjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.HashMap;

/**
 *
 * @author vernick
 */
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JWJava {

    /*
     * logger
    */
    private final static Logger LOGGER = Logger.getLogger(JWJava.class.getName());   
    
    /*
    ** Constructor
    */
    public JWJava() {
    }

    /* 
     * Function to test the JW Management API using the /videos/list API call
     */
    public void testMgmtAPI(JWApi api) {
        JWResponse response = null;
        HashMap hm = new HashMap<String, String>();
        hm.put("result_limit", "10");
        response = api.callMgmt("/videos/list", hm);
        LOGGER.log(Level.INFO, "Status Code: {0}", response.httpStatus);
        response.Show();

    }
    /*
     * Function to test the JW Delivery API
    */
    public void testDeliveryAPI(JWApi api) {
        JWResponse response = null;
        try {
            response = api.callDelivery("/media/icTgTyJm");
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        response.Show();
        JSONObject json = response.json;

        LOGGER.log(Level.INFO, "JSON Response: {0}", json.toString());
        LOGGER.log(Level.INFO, "Feed Instance {0}", json.get("feed_instance_id"));
        JSONArray playlist = (JSONArray) json.get("playlist");

        // Iterate over the result
        //
        @SuppressWarnings("unchecked")
        Iterator<JSONObject> it = playlist.iterator();
        while (it.hasNext()) {
            JSONObject item = it.next();
            LOGGER.log(Level.INFO, "PlayList: {0}", item.toString());
            JSONArray sources = (JSONArray) item.get("sources");
            Iterator<JSONObject> itSources = sources.iterator(); 
            while (itSources.hasNext()) {
                JSONObject source = itSources.next();
                LOGGER.log(Level.INFO, "Source: {0}", source.toString());                    
            }
        }            
    }
    public void Run() {
        JWResponse res = null;
        
        // Enter your JW Player Credentials here
        JWApi api = new JWApi("<APIKEY>", "<APISECRET");
        
        testDeliveryAPI(api);
        testMgmtAPI(api);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String json;
        
        LOGGER.setLevel(Level.INFO);  
        LOGGER.info("Logging Enabled.");
        
        JWJava runClass = new JWJava();
        runClass.Run();
    }
}
