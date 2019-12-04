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

    public static void main(String[] args) throws Exception {
        String apiKey = "key";
        String apiSecret = "secret";

        String videosCreatePath = "videos/create";
        Map<String, String> videosCreateParams = new HashMap<>();
        videosCreateParams.put("sourcetype", "url");
        videosCreateParams.put("sourceformat", "mp4");
        videosCreateParams.put("sourceurl", "http://www.some-url.com/some-video.mp4");
        videosCreateParams.put("title", "Some Video Title");

        String videosShowPath = "videos/show";
        
        try {            
            JWPlatformClient client = JWPlatformClient.create(apiKey, apiSecret);
            
            // Create a video asset
            JSONObject videosCreateResponse = client.request(videosCreatePath, videosCreateParams);
            System.out.println(videosCreateResponse);
            
            // Show the properties of the created video
            String videoKey = videosCreateResponse.getJSONObject("video").get("key").toString();
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

=======
## Supported operations

All API methods documentated on the API are available in this client. 
Please refer to our [api documentation](https://developer.jwplayer.com/jwplayer/reference).

## ChangeLog 
See the [Change Log](CHANGELOG.md) for recent changes.

## License

JW Platform API library is distributed under the
[Apache 2 license](LICENSE).