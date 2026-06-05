package lt.vikoeif.pi24.simple_soap_client;

import lt.vikoeif.pi24.wsdl.*;
import lt.vikoeif.pi24.simple_soap_client.xslgen.DealershipHtmlGenerator;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class SimpleSoapClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSoapClientApplication.class, args);
    }

    // Testing that the "getAllDealerships" method works
    @Bean
    ApplicationRunner lookup(DealershipClient client) {
        return args -> {
            // FIXME: Parse application command line args?
//            List<String> dealershipOption = args.getOptionValues("dealership");
//            String dealership =

            // Print dealerships
            // =================

            GetAllDealershipsResponse response = client.getAllDealerships();
            List<Dealership> dealershipList = response.getDealerships();

            Logger.logTitle("All Dealerships");
            for (Dealership dealership : dealershipList) {
                System.out.println(WsdlUtils.dealershipToString(dealership));
            }
            System.out.println("Dealership count: " + dealershipList.size());

            // Test JAXB marshaller
            // ====================

            Dealership dealership = new Dealership();
            dealership.setId(1);
            dealership.setName("Downtown Vegas Wheels");
            dealership.setPhone("123-456-7890");
            dealership.setLocation("Las Vegas");

            Dealership.Inventory inventory = new Dealership.Inventory();

            Car car1 = new Car();
            car1.setId(1);
            car1.setBrand("Toyota");
            car1.setModel("Camry");
            car1.setYear(2020);

            Car car2 = new Car();
            car2.setId(2);
            car2.setBrand("Toyota");
            car2.setModel("Corolla");
            car2.setYear(2015);

            Car car3 = new Car();
            car3.setId(3);
            car3.setBrand("Honda");
            car3.setModel("Civic");
            car3.setYear(2021);

            inventory.getCar().add(car1);
            inventory.getCar().add(car2);
            inventory.getCar().add(car3);

            dealership.setInventory(inventory);

            // Add this data to the database
            AddDealershipResponse dealershipResponse = client.addDealership(dealership);
            if (dealershipResponse.isSuccess()) {
                Logger.logVerboseMessage(
                        "Successfully added __TEST__ dealership data",
                        "Data: " + WsdlUtils.dealershipToString(dealership)
                );
            } else {
                Logger.logVerboseMessage("ERROR: could not add __TEST__ dealership data");
            }

            Logger.logTitle("App initialization has completed");
        };
    }

    // Testing that the "addDealership" SOAP method works
    /*@Bean
    ApplicationRunner create(DealershipClient client) {
        return args -> {

            // Mock a dealership
            Dealership dealership = new Dealership();
            dealership.setId(1);
            dealership.setName("New City Motors");
            dealership.setPhone("111-333-7777");
            dealership.setLocation("Los Angeles");

            AddDealershipResponse response = client.addDealership(dealership);
            System.out.println("\nAddDealershipResponse status: " + response.isSuccess() + "\n");
        };
    }*/
}
