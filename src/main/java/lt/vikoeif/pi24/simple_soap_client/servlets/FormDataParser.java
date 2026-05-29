package lt.vikoeif.pi24.simple_soap_client.servlets;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Static class for parsing HTML form POST data
 */
public class FormDataParser {

    /**
     * Parse HTML form data from raw POST data
     * @param postData the raw POST data string
     * @return key-value pairs mapping
     */
    public static Map<String, String> parseHtmlPostData(String postData) {
        Map<String, String> params = new HashMap<>();
        if (postData == null || postData.isEmpty()) {
            return params;
        }

        String[] pairs = postData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
                String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                params.put(key, value);
            }
        }
        return params;
    }
}
