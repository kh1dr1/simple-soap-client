package lt.vikoeif.pi24.simple_soap_client.controller;

import lt.viko.eif.pi24.dealership_service.schema.Dealership;
import lt.viko.eif.pi24.dealership_service.schema.GetAllDealershipsResponse;
import lt.vikoeif.pi24.simple_soap_client.endpoint.DealershipEndpointClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DealershipController {
    private static final Logger _logger = LoggerFactory.getLogger(DealershipController.class);

    private final DealershipEndpointClient dealershipEndpointClient;

    public DealershipController(DealershipEndpointClient dealershipEndpointClient) {
        this.dealershipEndpointClient = dealershipEndpointClient;
    }

    @GetMapping("/create-dealership")
    public String showForm() {
        return "create-dealership"; // Returns create-dealership.html from templates/
    }

    @PostMapping("/create-dealership")
    public String createDealership(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String location,
            Model model) {

        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("location", location);

        return "dealership-created";
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

        return "dealerships";
    }
}
