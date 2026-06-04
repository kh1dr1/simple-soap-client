package lt.vikoeif.pi24.simple_soap_client;

import lt.vikoeif.pi24.wsdl.*;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

            GetAllDealershipsResponse response = client.getAllDealerships();
            System.out.println("All dealerships are:");
            for (Dealership dealership : response.getDealerships()) {
                System.out.println(WsdlUtils.dealershipToString(dealership));
            }
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
