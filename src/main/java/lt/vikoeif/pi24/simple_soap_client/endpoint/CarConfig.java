package lt.vikoeif.pi24.simple_soap_client.endpoint;

import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CarConfig {

    @Bean
    public CarEndpointClient getCarClient(
            WebServiceTemplateBuilder builder,
            Jaxb2Marshaller marshaller
    ) {
        builder = builder.setMarshaller(marshaller).setUnmarshaller(marshaller);

        CarEndpointClient client = new CarEndpointClient();
        client.setWebServiceTemplate(builder.build());
        client.setDefaultUri("http://localhost:8080/ws");
        return client;
    }
}
