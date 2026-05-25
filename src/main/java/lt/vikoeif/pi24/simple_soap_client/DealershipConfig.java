package lt.vikoeif.pi24.simple_soap_client;

import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class DealershipConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package configured in the pom.xml
        marshaller.setContextPath("lt.vikoeif.pi24.wsdl");
        return marshaller;
    }

    @Bean
    public DealershipClient getDealershipClient(
            WebServiceTemplateBuilder builder, Jaxb2Marshaller marshaller
    ) {
        builder = builder.setMarshaller(marshaller).setUnmarshaller(marshaller);

        DealershipClient client = new DealershipClient();
        client.setWebServiceTemplate(builder.build());
        client.setDefaultUri("http://localhost:8081/ws/dealerships");
        return client;
    }
}
