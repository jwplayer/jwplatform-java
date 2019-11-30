# JW Platform API Client

The JWPlatform PHP library provides convenient access to the
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
        Map<String, String> params = new HashMap<String, Object>();
        params.put("sourcetype", "url");
        params.put("sourceformat", "mp4");
        params.put("sourceurl", "http://www.some-url.com/some-video.mp4");
        params.put("title", "Some Video Title");

        try {
            JWPlatformClient client = JWPlatformClient.create(apiKey, apiSecret);
            JSONObject response = client.request(videosCreatePath, params);
            System.out.println(response);
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
[MIT license](LICENSE).