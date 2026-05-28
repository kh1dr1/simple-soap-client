package lt.vikoeif.pi24.simple_soap_client;

import lt.vikoeif.pi24.wsdl.Dealership;
import lt.vikoeif.pi24.wsdl.GetAllDealershipsResponse;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleSoapClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSoapClientApplication.class, args);
    }

    @Bean
    ApplicationRunner lookup(DealershipClient client) {
        return args -> {
            // FIXME: Parse application command line args?
//            List<String> dealershipOption = args.getOptionValues("dealership");
//            String dealership =

            GetAllDealershipsResponse response = client.getAllDealerships();
            System.out.println("All dealerships are:");
            for (Dealership dealership : response.getDealerships()) {
                System.out.println(dealershipToString(dealership));
            }
        };
    }

    private static String dealershipToString(Dealership dealership) {
        return "Dealership {\n" +
                "  ID: " + dealership.getId() + ";\n" +
                "  Name: " + dealership.getName() + ";\n" +
                "  Location: " + dealership.getLocation() + ";\n" +
                "  Phone: " + dealership.getPhone() + ";\n" +
                "}";
    }
}
