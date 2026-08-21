package lt.vikoeif.pi24.simple_soap_client.xslt;

import jakarta.xml.bind.JAXBElement;

import lt.viko.eif.pi24.dealership_service.schema.*;

import javax.xml.namespace.QName;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.StringReader;

public final class DealershipHtmlGenerator {
    public static final String XSL_PATH = "/xsl/dealership-to-html.xsl";

    private static final Logger _logger = LoggerFactory.getLogger(DealershipHtmlGenerator.class);

    public static String dealershipToHtml(Dealership dealership) throws Exception {
        // 1. Convert Dealership object to XML string
        String xmlString = marshalToXml(dealership);
        //_logger.info("Input XML / before XSLT:\n{}", xmlString);

        // 2. Apply XSLT transformation
        String htmlString = transformXmlToHtml(xmlString);
        //_logger.info("Output HTML / after XSLT:\n{}", htmlString);

        return htmlString;
    }

    private static String marshalToXml(Dealership dealership) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Dealership.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();

        // Create a QName (qualified name) for the root element
        QName qname = new QName("http://eif.viko.lt/dealership-service/schema", "dealership");

        // Wrap the object in JAXBElement
        JAXBElement<Dealership> wrapper = new JAXBElement<>(qname, Dealership.class, dealership);

        // Marshal the wrapped object
        marshaller.marshal(wrapper, sw);

        return sw.toString();
    }

    private static String transformXmlToHtml(String xmlString) throws Exception {
        // Load XSLT from classpath
        InputStream xslStream = DealershipHtmlGenerator
                .class
                .getResourceAsStream(XSL_PATH);

        _logger.info("Found XSL transformation file: {}", (xslStream != null));

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
