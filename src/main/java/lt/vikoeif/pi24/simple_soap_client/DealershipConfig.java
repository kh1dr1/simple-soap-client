package lt.vikoeif.pi24.simple_soap_client;

import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class DealershipConfig {

    /**
     * Spring Bean method which sets up a JAXB marshaller for SOAP operations.
     * A JAXB marshaller is necessary to convert Java objects to XML
     * before they are sent in a SOAP message.
     * @return Jaxb2Marshaller
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        /*
            This package must match the package configured in the pom.xml.
            This is where JAXB-generated classes live.
         */
        marshaller.setContextPath("lt.vikoeif.pi24.wsdl");
        return marshaller;
    }

    /**
     * Spring Bean method which configures the SOAP service client.
     * The DealershipClient is configured to use the correct web service URI.
     * @param builder WebServiceTemplateBuilder
     * @param marshaller Jaxb2Marshaller
     * @return DealershipClient
     */
    @Bean
    public DealershipClient getDealershipClient(
            WebServiceTemplateBuilder builder,
            Jaxb2Marshaller marshaller
    ) {
        builder = builder.setMarshaller(marshaller).setUnmarshaller(marshaller);

        DealershipClient client = new DealershipClient();
        client.setWebServiceTemplate(builder.build());
        client.setDefaultUri("http://localhost:8081/ws/dealerships");
        return client;
    }
}
