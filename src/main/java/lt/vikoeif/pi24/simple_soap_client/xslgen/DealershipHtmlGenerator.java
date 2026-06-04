package lt.vikoeif.pi24.simple_soap_client.xslgen;

import lt.vikoeif.pi24.wsdl.Dealership;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.StringReader;

public class DealershipHtmlGenerator {

    public String dealershipToHtml(Dealership dealership) throws Exception {
        // 1. Convert Dealership object to XML string
        String xmlString = marshalToXml(dealership);

        // 2. Apply XSLT transformation
        String htmlString = transformXmlToHtml(xmlString);

        return htmlString;
    }

    private String marshalToXml(Dealership dealership) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Dealership.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        marshaller.marshal(dealership, sw);
        return sw.toString();
    }

    private String transformXmlToHtml(String xmlString) throws Exception {
        // Load XSLT from classpath
        InputStream xslStream = getClass().getResourceAsStream("/xsl/dealership-to-html.xsl");
        Source xslSource = new StreamSource(xslStream);

        // Create transformer
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslSource);

        // Transform XML to HTML
        Source xmlSource = new StreamSource(new StringReader(xmlString));
        StringWriter htmlWriter = new StringWriter();
        Result htmlResult = new StreamResult(htmlWriter);

        transformer.transform(xmlSource, htmlResult);

        return htmlWriter.toString();
    }
}
