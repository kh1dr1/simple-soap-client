package lt.vikoeif.pi24.simple_soap_client.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lt.vikoeif.pi24.simple_soap_client.endpoint.DealershipEndpointClient;
import lt.viko.eif.pi24.dealership_service.schema.*;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDealershipServlet extends HttpServlet {
    private static final Logger _logger = LoggerFactory.getLogger(CreateDealershipServlet.class);

    private final DealershipEndpointClient dealershipEndpointClient;

    public CreateDealershipServlet(DealershipEndpointClient dealershipEndpointClient) {
        this.dealershipEndpointClient = dealershipEndpointClient;
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

        // TODO: check for blank fields
        _logger.info("""
                \n
                ===== HTTP POST data =====
                Dealership name: {}
                Dealership phone: {}
                Dealership location: {}
                """,
                dealershipName,
                dealershipPhone,
                dealershipLocation);

        // Set response content type
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Generate response page
        out.printf("""
            <!DOCTYPE html>
            <html lang='en-US'>
            <head>
                <title>Dealership Created</title>
                <meta charset='UTF-8'>
            </head>
            <body>
                <h1>Dealership: %s</h1>
                <p>Information:</p>
                <ul>
                    <li>Phone: %s</li>
                    <li>Location: %s</li>
                </ul>
                <a href='/create-dealership.html'>Create Another</a>
                <a href='/index.html'>Back to Main</a>
            </body>
        """, dealershipName, dealershipPhone, dealershipLocation);

        // Create a dealership object
        Dealership dealership = new Dealership();
        dealership.setId(0);
        dealership.setName(dealershipName);
        dealership.setPhone(dealershipPhone);
        dealership.setLocation(dealershipLocation);

        // Send a SOAP message to the server
        AddDealershipResponse response = dealershipEndpointClient.addDealership(dealership);
        if (response.isSuccess()) {
            _logger.info("Created a new Dealership with ID: {}", dealership.getId());
        } else {
            _logger.warn("Cannot create a Dealership");
        }
    }
}
