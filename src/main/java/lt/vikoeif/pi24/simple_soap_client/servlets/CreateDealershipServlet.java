package lt.vikoeif.pi24.simple_soap_client.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lt.vikoeif.pi24.simple_soap_client.DealershipClient;
import lt.vikoeif.pi24.wsdl.AddDealershipResponse;
import lt.vikoeif.pi24.wsdl.Dealership;

import java.io.IOException;
import java.io.PrintWriter;

public class CreateDealershipServlet extends HttpServlet {

    private final DealershipClient dealershipClient;

    public CreateDealershipServlet(DealershipClient dealershipClient) {
        this.dealershipClient = dealershipClient;
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException {

        // Get form data from POST request
        String dealershipName = req.getParameter("name");
        String dealershipPhone = req.getParameter("phone");
        String dealershipLocation = req.getParameter("location");

        System.out.println("===== HTTP POST data =====");
        System.out.println("Dealership name: " + dealershipName);
        System.out.println("Dealership phone: " + dealershipPhone);
        System.out.println("Dealership location: " + dealershipLocation + "\n");

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

        // Create a dealership object
        Dealership dealership = new Dealership();

        /*
            NOTE: ID field has no effect, as the database will generate a
            different ID for a new Dealership. But this field is necessary
            when sending a SOAP request.
         */
        dealership.setId(10000);
        dealership.setName(dealershipName);
        dealership.setPhone(dealershipPhone);
        dealership.setLocation(dealershipLocation);

        // Send a SOAP message to the server
        AddDealershipResponse response = dealershipClient.addDealership(dealership);
        if (response.isSuccess()) {
            System.out.println("Created a new Dealership");
        } else {
            System.out.println("error: cannot create a Dealership");
        }
    }
}
