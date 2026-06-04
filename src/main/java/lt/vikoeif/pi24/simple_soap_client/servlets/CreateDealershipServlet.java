package lt.vikoeif.pi24.simple_soap_client.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lt.vikoeif.pi24.simple_soap_client.DealershipClient;
import lt.vikoeif.pi24.simple_soap_client.Logger;
import lt.vikoeif.pi24.simple_soap_client.WsdlUtils;
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

        // Get last dealership ID
        int dealershipCount = dealershipClient
                .getDealershipCount()
                .getCount();

        Logger.logVerboseMessage("Current Dealership count",
                "Current Dealership count: " + dealershipCount
        );

        // Create a dealership object
        Dealership dealership = new Dealership();
        dealership.setName(dealershipName);
        dealership.setPhone(dealershipPhone);
        dealership.setLocation(dealershipLocation);

        // Send a SOAP message to the server
        AddDealershipResponse response = dealershipClient.addDealership(dealership);
        if (response.isSuccess()) {
            Logger.logVerboseMessage("Created a new Dealership",
                    WsdlUtils.dealershipToString(dealership)
            );
        } else {
            Logger.logVerboseMessage("ERROR", "Cannot create a Dealership");
        }
    }
}
