package lt.vikoeif.pi24.simple_soap_client.controller;

import lt.viko.eif.pi24.dealership_service.schema.*;
import lt.vikoeif.pi24.simple_soap_client.endpoint.DealershipEndpointClient;
import lt.vikoeif.pi24.simple_soap_client.xslgen.DealershipHtmlGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DealershipController {
    private static final Logger _logger = LoggerFactory.getLogger(DealershipController.class);

    private final DealershipEndpointClient dealershipEndpointClient;

    public DealershipController(DealershipEndpointClient dealershipEndpointClient) {
        this.dealershipEndpointClient = dealershipEndpointClient;
    }

    @GetMapping("/create-dealership")
    public String createDealership() {
        return "create-dealership"; // Returns create-dealership.html from templates/
    }

    @PostMapping("/create-dealership")
    public String createDealership(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String location,
            Model model) {

        // Create a dealership object
        Dealership dealership = new Dealership();
        dealership.setId(0); // ID is set by the database
        dealership.setName(name);
        dealership.setPhone(phone);
        dealership.setLocation(location);

        // Send a SOAP message to the server
        AddDealershipResponse response = dealershipEndpointClient.addDealership(dealership);
        if (response.isSuccess()) {
            _logger.info("Created a new Dealership with ID: {}", dealership.getId());
        } else {
            _logger.warn("Cannot create a Dealership");
        }

        // Assign model data for the returned HTML page model
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("location", location);

        return "create-dealership-success";
    }

    @GetMapping("/dealerships")
    public String getAllDealerships(Model model) {

        List<Dealership> dealershipList = List.of();
        try {
            GetAllDealershipsResponse allDealershipsResponse = dealershipEndpointClient.getAllDealerships();
            dealershipList = allDealershipsResponse.getDealership();
        } catch (Exception ex) {
            _logger.warn("Cannot send SOAP request: 'getAllDealerships'. Error: {}", ex.getMessage());
        }

        model.addAttribute("dealerships", dealershipList);

        return "all-dealerships";
    }

    /**
     * Returns a response page with a choice of items list, populated
     * with a List of all Dealerships.
     */
    @GetMapping("/dealership-choice")
    public String dealershipChoice(Model model) {
        List<Dealership> dealershipList;

        // Populate the list of dealerships
        // And send it to the client
        GetAllDealershipsResponse allDealershipsResponse = dealershipEndpointClient.getAllDealerships();
        dealershipList = allDealershipsResponse.getDealership();
        model.addAttribute("dealerships", dealershipList);

        // Return Thymeleaf template: "dealership-choice.html"
        return "dealership-choice";
    }

    /**
     * This method handles "get dealership by ID" queries.
     * @param dealershipId URL query parameter, e.g. {@code ?id=1}
     * @return raw HTML string
     */
    @PostMapping("/dealership-choice")
    @ResponseBody
    public String getDealership(
            @RequestParam int dealershipId,
            Model model
    ) {

        // Get a dealership by ID
        GetDealershipByIdResponse response = dealershipEndpointClient.getDealershipById(dealershipId);
        Dealership dealership = response.getDealership();

        // Generate HTML via XSLT
        String xsltHtml;
        try {
            xsltHtml = DealershipHtmlGenerator.dealershipToHtml(dealership);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return xsltHtml;
    }

    @GetMapping("/get-dealership-cars")
    public String getDealershipCars(
            @RequestParam int id,
            Model model
    ) {
        GetDealershipCarsResponse response = dealershipEndpointClient.getDealershipCars(id);
        List<Car> carList = response.getCar();

        model.addAttribute("dealershipId", id);
        model.addAttribute("cars", carList);

        // HTML page: dealership-cars.html
        return "dealership-cars";
    }
}
