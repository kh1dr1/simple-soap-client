package lt.vikoeif.pi24.simple_soap_client.controller;

import lt.viko.eif.pi24.dealership_service.schema.*;
import lt.vikoeif.pi24.simple_soap_client.endpoint.CarEndpointClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {
    private static final Logger _logger = LoggerFactory.getLogger(CarController.class);

    private final CarEndpointClient carEndpointClient;

    public CarController(CarEndpointClient carEndpointClient) {
        this.carEndpointClient = carEndpointClient;
    }

    @GetMapping("/create-car")
    public String showForm() {
        return "create-car";
    }

    @PostMapping("/create-car")
    public String createCar(
            @RequestParam String status,
            @RequestParam String brand,
            @RequestParam String model,
            @RequestParam int year,
            @RequestParam String color,
            @RequestParam int price,
            @RequestParam int dealershipId,
            Model uiModel
    ) {

        _logger.info("""
                \n
                ==== FORM DATA ====
                Status: {}
                Brand: {}
                Model: {}
                Year: {}
                Color: {}
                Price: {}
                Dealership ID: {}
                """,
                status,
                brand,
                model,
                year,
                color,
                price,
                dealershipId
        );

        // Create a Car object
        Car car = new Car();
        car.setId(0);
        car.setStatus(CarStatus.valueOf(status.toUpperCase()));
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setColor(CarColor.valueOf(color.toUpperCase()));
        car.setPrice(price);
        car.setDealershipId(dealershipId);

        // Send a SOAP request to the server
        AddCarResponse addCarResponse = carEndpointClient.addCar(car);
        if (addCarResponse.isSuccess()) {
            _logger.info("Created a new Car with ID: {}", car.getId());
        } else {
            _logger.warn("Cannot create a new Car");
        }

        // Assign model data for the returned HTML page model
        uiModel.addAttribute("status", status);
        uiModel.addAttribute("brand", brand);
        uiModel.addAttribute("model", model);
        uiModel.addAttribute("year", year);
        uiModel.addAttribute("color", color);
        uiModel.addAttribute("price", price);
        uiModel.addAttribute("dealershipId", dealershipId);

        return "create-car-success";
    }

    @GetMapping("/cars")
    public String getAllCars(Model model) {
        List<Car> carList = List.of();
        try {
            GetAllCarsResponse allCarsResponse = carEndpointClient.getAllCars();
            carList = allCarsResponse.getCar();
        } catch (Exception ex) {
            _logger.warn("Cannot send SOAP request: 'getAllCars'. Error: {}", ex.getMessage());
        }

        model.addAttribute("cars", carList);

        return "all-cars";
    }
}
