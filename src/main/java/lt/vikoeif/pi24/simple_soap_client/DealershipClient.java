package lt.vikoeif.pi24.simple_soap_client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import lt.vikoeif.pi24.wsdl.*;

public class DealershipClient extends WebServiceGatewaySupport {
    public final String WS_REQUEST_URI = "http://localhost:8081/ws/dealerships";

    /**
     * Get a list of all dealerships
     * @return GetAllDealershipsResponse
     */
    public GetAllDealershipsResponse getAllDealerships() {
        GetAllDealershipsRequest request = new GetAllDealershipsRequest();

        return (GetAllDealershipsResponse)
                getWebServiceTemplate().marshalSendAndReceive(
                        WS_REQUEST_URI,
                        request
                );
    }

    /**
     * Create a dealership
     * @param dealership the dealership object data
     * @return AddDealershipResponse
     */
    public AddDealershipResponse addDealership(Dealership dealership) {
        AddDealershipRequest request = new AddDealershipRequest();
        request.setDealership(dealership);

        return (AddDealershipResponse)
                getWebServiceTemplate().marshalSendAndReceive(
                        WS_REQUEST_URI,
                        request
                );
    }
}
