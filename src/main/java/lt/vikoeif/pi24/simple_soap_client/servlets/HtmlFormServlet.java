package lt.vikoeif.pi24.simple_soap_client.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HtmlFormServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        // Return HTML form string
        // NOTE: form action is registered at "/submit" URI
        out.println("""
                <html><body>
                
                <h1>Hello</h1>
                
                <form action="/submit" method="post">
                  <label for="fname">First name:</label><br>
                  <input type="text" id="fname" name="fname"><br>
                  <label for="lname">Last name:</label><br>
                  <input type="text" id="lname" name="lname">
                  
                  <!-- Submit button -->
                  <input type="submit" value="Submit Form">
                  
                </form>
                
                </body></html>
        """);

        out.println("");
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        BufferedReader in = req.getReader();

        // Read all lines
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            requestBody.append(line);
        }

        String postData = requestBody.toString();
        System.out.println("Raw POST data: " + postData);

        // Parse the parameters
        Map<String, String> params = parsePostData(postData);
        System.out.println("First Name: " + params.get("fname"));
        System.out.println("Last Name: " + params.get("lname"));
    }

    private Map<String, String> parsePostData(String postData) {
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
