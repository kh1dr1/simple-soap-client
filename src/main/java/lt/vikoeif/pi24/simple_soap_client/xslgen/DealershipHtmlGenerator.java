package lt.vikoeif.pi24.simple_soap_client.xslgen;

import jakarta.xml.bind.JAXBElement;
import lt.vikoeif.pi24.simple_soap_client.Logger;
import lt.vikoeif.pi24.wsdl.Dealership;

import javax.xml.namespace.QName;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.StringReader;

public final class DealershipHtmlGenerator {
    public static final String XSL_PATH = "/xsl/dealership-to-html.xsl";

    public static String dealershipToHtml(Dealership dealership) throws Exception {
        // 1. Convert Dealership object to XML string
        String xmlString = marshalToXml(dealership);
        Logger.logVerboseMessage("Input XML (before XSLT)", xmlString);

        // 2. Apply XSLT transformation
        String htmlString = transformXmlToHtml(xmlString);
        Logger.logVerboseMessage("Transformed XML -> HTML", htmlString);

        return htmlString;
    }

    private static String marshalToXml(Dealership dealership) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Dealership.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();

        // Create a QName (qualified name) for the root element
        QName qname = new QName("http://your.namespace.com", "dealership");

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
        System.out.println("[INFO] XSL file exists: " + (xslStream != null));

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
