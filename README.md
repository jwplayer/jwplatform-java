# JW Platform API Client

The JWPlatform library provides convenient access to the
[JW Platform](https://www.jwplayer.com/products/jwplatform/)
Management API from applications written in the Java language.

Visit [JW Player Developer site](https://developer.jwplayer.com/jw-platform/)
for more information about JW Platform API.

## Requirements

Java 8 and later.

## Install With Maven:

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.jwplayer</groupId>
  <artifactId>jwplatform</artifactId>
  <version>0.3.1</version>
</dependency>
```

## Usage

The following is an example of how to use the client for a video of sourcetype `url`:

```java

import com.jwplayer.jwplatform.JWPlatformClient;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class JWPlatformClientExample {

    public static void main(String[] args) {
        String apiKey = "key";
        String apiSecret = "secret";

        String videosCreatePath = "videos/create";
        Map<String, String> videosCreateParams = new HashMap<>();
        videosCreateParams.put("sourcetype", "url");
        videosCreateParams.put("sourceformat", "mp4");
        videosCreateParams.put("sourceurl", "http://www.some-url.com/some-video.mp4");
        videosCreateParams.put("title", "Some Video Title");

        String videosShowPath = "/videos/show";
        
        try {            
            JWPlatformClient client = JWPlatformClient.create(apiKey, apiSecret);
            
            // Create a video asset
            JSONObject videosCreateResponse = client.request(videosCreatePath, videosCreateParams);
            System.out.println(videosCreateResponse);
            
            // Show the properties of the created video
            String videoKey = videosCreateResponse.getJSONObject("video").getString("key");
            Map<String, String> videosShowParams = new HashMap<>();
            videosShowParams.put("video_key", videoKey);
            JSONObject videosShowResponse = client.request(videosShowPath, videosShowParams);
            System.out.println(videosShowResponse);
        } catch (JWPlatformException e) {
            e.printStackTrace();
        }
    }
}

```

The following is an example of how to use the client for a video of sourcetype `file`:

```java

import com.jwplayer.jwplatform.JWPlatformClient;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class JWPlatformClientExample {

    public static void main(String[] args) {
    String apiKey = "key";
    String apiSecret = "secret";

    String videosCreatePath = "/videos/create";
    Map<String, String> videosCreateParams = new HashMap<>();
    videosCreateParams.put("sourcetype", "file");
    videosCreateParams.put("title", "Some Video Title");

    String localFilePath = "/some/path/test_video.mp4";

    try {
        JWPlatformClient client = JWPlatformClient.create(apiKey, apiSecret);

        // Create a video asset
        JSONObject videosCreateResponse = client.request(videosCreatePath, videosCreateParams);
        System.out.println(videosCreateResponse);

        // Upload the video from local file system
        JSONObject videoUploadResponse = client.upload(videosCreateResponse, localFilePath);
        System.out.println(videoUploadResponse);
    } catch (JWPlatformException e) {
        e.printStackTrace();
    }
}
```
_**Note**_

In the preceding snippets, all URL paths feature a leading slash. These must be included
for the URL builder to work properly.

## Supported operations

All API methods documentated on the API are available in this client. 
Please refer to our [api documentation](https://developer.jwplayer.com/jwplayer/reference).

## ChangeLog 
See the [Change Log](CHANGELOG.md) for recent changes.

## License

JW Platform API library is distributed under the
[Apache 2 license](LICENSE).
