package jwjava;


import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author vernick
 */
public class JWResponse {
    public int httpStatus;
    public JSONObject json;   
    public int remaining;
    public int resetTime;
    
    
    public JWResponse() {
        this.httpStatus = 0;
        this.json = null;
        this.remaining = 1000;
        this.resetTime = 0;
    }
    public void Show() {
        System.out.println(String.format("HttpStatus: %d, Remaining: %d, Expires: %d", 
                httpStatus, remaining, resetTime));
        System.out.println(json);
    }
    public void Parse(CloseableHttpResponse response) {
        httpStatus = response.getStatusLine().getStatusCode();
        HttpEntity body = response.getEntity();
        if (body != null) {
            try {
                // Convert the body into a string, then parse it as a JSON object.
                String retSrc = EntityUtils.toString(body); 
                
                // parsing JSON
                Object obj = JSONValue.parse(retSrc);
                json = (JSONObject) obj;
                
                // Is there a rate limit in the response?
                //
                JSONObject rate;
                rate = (JSONObject) json.get("rate_limit");
                if ((rate != null) && !rate.isEmpty()) {
                    remaining = Integer.parseInt(rate.get("remaining").toString());
                    resetTime = Integer.parseInt(rate.get("reset").toString());
                }
            } catch (IOException ex) {
                
            }
        }              
    }
}
