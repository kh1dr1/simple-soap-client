package lt.vikoeif.pi24.simple_soap_client.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CreateDealershipServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        // Get form data from POST request
        String dealershipName = req.getParameter("name");
        String dealershipPhone = req.getParameter("phone");
        String dealershipLocation = req.getParameter("location");

        // Set response content type
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Generate response page
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Form Submitted</title></head>");
        out.println("<body>");
        out.println("<h1>Name: " + dealershipName + "</h1>");
        out.println("<p>Information:</p>");
        out.println("<ul>");
        out.println("<li>Phone: " + dealershipPhone + "</li>");
        out.println("<li>Location: " + dealershipLocation + "</li>");
        out.println("</ul>");
        out.println("<a href='/create-dealership.html'>Back to Form</a>");
        out.println("</body>");
        out.println("</html>");

        // FIXME: Send a SOAP message to the server
    }
}
