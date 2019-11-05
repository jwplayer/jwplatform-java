package jwjava;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import java.util.Iterator;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author vernick
 */
public class JWApi {
    String _key, _secret;
    String _mgmthost = "api.jwplatform.com/v1";
    String _deliveryHost = "cdn.jwplayer.com/v2";
    private final static Logger LOGGER = Logger.getLogger(JWJava.class.getName());       
    
    /* 
     * Constructor. Just save api key and secret
    */
    public JWApi (String key, String secret) {
        this._key = key;
        this._secret = secret;
    }
    
    // Signs a list of name/value pairs
    //
    private String sign(List<NameValuePair> params) {
        // First need to sort the list by the name.
        //
        Comparator<NameValuePair> comp = new Comparator<NameValuePair>() {        // solution than making method synchronized
            @Override
            public int compare(NameValuePair p1, NameValuePair p2) {
                return p1.getName().compareTo(p2.getName());
            }
        };
        Collections.sort(params, comp);
        
        // Construct the query string
        String str = "";
        // Iterate over the name value pairs
        Iterator it = params.iterator();
        while (it.hasNext()) {
            if (str != "") {
                str = str + "&";
            }
            BasicNameValuePair bnp = (BasicNameValuePair) it.next();
            str = str + bnp.getName() + "=" + bnp.getValue();
        }
        System.out.println(str);
        String sig = DigestUtils.sha1Hex(str + _secret);
        return sig;
    }
    private void buildQuery(List<NameValuePair> params) {
        String qString;
        
        // Make sure required parameters are included
        //
        params.add(new BasicNameValuePair("api_format", "json"));
        Random rnd = new Random();
        params.add(new BasicNameValuePair("api_nonce", String.format("%08d", rnd.nextInt(99999999))));
        params.add(new BasicNameValuePair("api_timestamp", Long.toString(System.currentTimeMillis() / 1000L)));
        params.add(new BasicNameValuePair("api_key", _key));
        params.add(new BasicNameValuePair("api_signature", sign(params)));
    }
    public List<NameValuePair> convertParams(HashMap<String, String> params) {
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        
        // Scan the hash map for each key/value pair
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            urlParameters.add(new BasicNameValuePair(pair.getKey().toString(), pair.getValue().toString()));             
        }
        it.remove(); // avoids a ConcurrentModificationException

        return urlParameters;
    }
    private JWResponse executeRequest(HttpGet request) {
        CloseableHttpResponse httpResponse = null;
        JWResponse jwResponse = new JWResponse();                
        
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();            
            httpResponse = httpClient.execute(request);
            jwResponse.Parse(httpResponse);
            
        } catch (IOException ex) {
            System.out.println("IO Exception in Sending Request");
        }
        
        try {
            if (httpResponse != null) httpResponse.close();            
        } catch (IOException ex) {
            System.out.println("IO Exception in closing response");
        }
        return jwResponse;
    }
    
    public JWResponse callMgmt (String route, HashMap<String, String> params) {
        // Locals
        URI uri;
        HttpGet request = new HttpGet();

        // Convert the input parameters into HTTP parameters
        //
        List<NameValuePair> args = convertParams(params);
        
        // Build the query arguments
        //
        buildQuery(args);
        
        try {
            // Create the GET string
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(_mgmthost).setPath(route);
            builder.addParameters(args);

            uri = builder.build();
            System.out.println(uri.toString());

            // Set the URI for the request.  Includes all the query parameters
            //
            request.setURI(uri);
        } catch (URISyntaxException ex) {
            // Failed to Setup the Request
        }     
        
        // Execute the request
        //
        JWResponse resp = executeRequest(request);
        return resp;
    }
    public JWResponse callDelivery(String route) throws IOException {
        LOGGER.log(Level.INFO, "calling {0}", route);
        String url = "http://" + _deliveryHost + route;

        // Just send a request to the URL
        JWResponse response = executeRequest(new HttpGet(url));
        return response;
    }
}
