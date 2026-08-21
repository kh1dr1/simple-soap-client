package lt.vikoeif.pi24.simple_soap_client.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lt.viko.eif.pi24.dealership_service.schema.Dealership;
import lt.viko.eif.pi24.dealership_service.schema.GetDealershipByIdResponse;
import lt.vikoeif.pi24.simple_soap_client.HtmlFormUtils;
import lt.vikoeif.pi24.simple_soap_client.XsdUtils;
import lt.vikoeif.pi24.simple_soap_client.endpoint.DealershipEndpointClient;
import lt.vikoeif.pi24.simple_soap_client.xslgen.DealershipHtmlGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class GetDealershipServlet extends HttpServlet {
    private static final Logger _logger = LoggerFactory.getLogger(GetDealershipServlet.class);

    private final DealershipEndpointClient dealershipEndpointClient;

    public GetDealershipServlet(DealershipEndpointClient dealershipEndpointClient) {
        this.dealershipEndpointClient = dealershipEndpointClient;
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException {

        // Get URL parameters
        Optional<Integer> maybeIntId = HtmlFormUtils.parseIntFormInput(req.getParameter("id"));
        if (maybeIntId.isEmpty()) {
            resp.sendError(400, "Missing dealership ID"); // HTTP 400 Bad Request
            return;
        }
        int idValue = maybeIntId.get();

        // Send SOAP request: 'getDealershipById'
        // TODO: catch SoapFaultClientException exception from 'getDealershipById'
        GetDealershipByIdResponse response = dealershipEndpointClient.getDealershipById(idValue);
        Dealership dealership = response.getDealership();

        if (dealership == null) {
            _logger.warn("There is no Dealership with ID={}", idValue);
            return;
        }

        _logger.info("Received a Dealership with ID={}", idValue);

        // Convert Dealership XML to HTML with XSL transformation
        String htmlDealership;
        try {
            _logger.info("Converting a Dealership to HTML via XSLT...");
            _logger.info("Dealership is:\n{}", XsdUtils.dealershipToString(dealership));
            htmlDealership = DealershipHtmlGenerator.dealershipToHtml(dealership);
        } catch (Exception e) {
            _logger.warn("Cannot convert a Dealership to HTML: {}", e.getMessage());
            return;
        }

        // Send HTML as response
        resp.setContentType("text/html");
        resp.getWriter().write(htmlDealership);
    }
}
