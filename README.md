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
    TODO
```

## Usage

The following is an example of how to use the client.

```java

import com.jwplayer.jwplatform.JWPlatformClient;
import com.jwplayer.jwplatform.exception.JWPlatformException;

public class JWPlatformClientExample {

    public static void main(String[] args) {
        String apiKey = "key";
        String apiSecret = "secret";

        String videosCreatePath = "videos/create";
        Map<String, String> videosCreateParams = new HashMap<String, Object>();
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
            String videoKey = response.getJSONObject("video").get("Key");
            Map<String, String> videosShowParams = new HashMap<String, Object>();
            videosShowParams.put("video_key", videoKey);
            JSONObject videosShowResponse = client.request(videosShowPath, videosShowParams);
            System.out.println(videosShowResponse);
        } catch (JWPlatformException e) {
            e.printStackTrace();
        }
    }
}

```

## ChangeLog 
See the [Change Log](CHANGELOG.md) for recent changes.

## License

JW Platform API library is distributed under the
[Apache 2 license](LICENSE).