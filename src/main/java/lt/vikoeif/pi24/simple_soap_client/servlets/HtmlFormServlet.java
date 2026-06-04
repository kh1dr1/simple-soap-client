package lt.vikoeif.pi24.simple_soap_client.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlFormServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException {
        PrintWriter out = resp.getWriter();

        // Return HTML form string
        // NOTE: form action is registered at "/submit" URI
        out.println("""
                <html><body>
               \s
                <h1>Hello</h1>
               \s
                <form action="/submit" method="post">
                  <label for="fname">First name:</label><br>
                  <input type="text" id="fname" name="fname"><br>
                  <label for="lname">Last name:</label><br>
                  <input type="text" id="lname" name="lname">
                 \s
                  <!-- Submit button -->
                  <input type="submit" value="Submit Form">
                 \s
                </form>
               \s
                </body></html>
        """);

        out.println("");
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException {

        // Get the POST parameters
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");

        /*
            The following code is just an example of how you could get raw
            POST string (but that is not necessary for getting form parameters,
            as we have the HttpServletRequest.getParameter method).
         */
        BufferedReader in = req.getReader();

        // Read all lines
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            requestBody.append(line);
        }

        String postData = requestBody.toString();
        System.out.println("Raw POST data: " + postData);

        // Log POST parameters
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
    }
}
