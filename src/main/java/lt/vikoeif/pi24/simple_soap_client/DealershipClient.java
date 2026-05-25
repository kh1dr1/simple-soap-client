package lt.vikoeif.pi24.simple_soap_client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import lt.vikoeif.pi24.wsdl.*;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class DealershipClient extends WebServiceGatewaySupport {
    public final String WS_NAMESPACE = "http://viko.lt/dealership-service/schema/";
    public final String WS_DEALERSHIPS_PORT = WS_NAMESPACE + "DealershipsPort/";

    public GetAllDealershipsResponse getAllDealerships() {
        GetAllDealershipsRequest request = new GetAllDealershipsRequest();

        GetAllDealershipsResponse response = (GetAllDealershipsResponse)
                getWebServiceTemplate().marshalSendAndReceive(
                        "http://localhost:8081/ws/dealerships",
                        request/*,
                        new SoapActionCallback(
                                WS_DEALERSHIPS_PORT + "getAllDealershipsRequest"
                        )*/
                );

        return response;
    }
}
