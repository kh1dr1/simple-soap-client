package lt.vikoeif.pi24.simple_soap_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DealershipController {

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
}
