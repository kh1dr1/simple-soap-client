package lt.vikoeif.pi24.simple_soap_client.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lt.vikoeif.pi24.simple_soap_client.DealershipClient;
import lt.vikoeif.pi24.simple_soap_client.Logger;
import lt.vikoeif.pi24.simple_soap_client.WsdlUtils;
import lt.vikoeif.pi24.simple_soap_client.xslgen.DealershipHtmlGenerator;
import lt.vikoeif.pi24.wsdl.*;

import java.io.IOException;

public class GetDealershipServlet extends HttpServlet {
    private final DealershipClient dealershipClient;

    public GetDealershipServlet(DealershipClient dealershipClient) {
        this.dealershipClient = dealershipClient;
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException {

        // Get URL parameters
        String dealershipId = req.getParameter("id");

        if (dealershipId == null || dealershipId.isBlank()) {
            resp.sendError(400, "Missing dealership ID"); // HTTP 400 Bad Request
            return;
        }

        int idNumber;
        try {
            idNumber = Integer.parseInt(dealershipId);
        } catch (NumberFormatException e) {
            System.out.println(
                    "[error] Dealership ID value " +
                    dealershipId +
                    " is not a parsable number"
            );
            resp.sendError(400, "Malformed dealership ID");
            return;
        }

        // SOAP request: get Dealership by ID
        Dealership dealership = getDealershipById(idNumber);
        if (dealership == null) {
            System.out.println("There is no such dealership: ID=" + idNumber);
            return;
        }

        Logger.logVerboseMessage("Received a Dealership by ID: " + idNumber,
                WsdlUtils.dealershipToString(dealership)
        );

        // Convert to HTML code via XSLT
        String htmlDealership;
        try {
            htmlDealership = DealershipHtmlGenerator.dealershipToHtml(dealership);
        } catch (Exception e) {
            System.out.println("Cannot convert a Dealership to HTML: " + e.getMessage());
            return;
        }

        // Use HTML code as response
        resp.setContentType("text/html");
        resp.getWriter().write(htmlDealership);
    }

    // FIXME: should be in a separate helper class, probably
    private Dealership getDealershipById(int id) {
        GetAllDealershipsResponse request = dealershipClient.getAllDealerships();
        return request.getDealerships().stream()
                .filter(d -> d.getId() == id)
                .findFirst().orElse(null);
    }
}
