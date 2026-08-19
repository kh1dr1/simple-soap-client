package lt.vikoeif.pi24.simple_soap_client.endpoint;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import lt.viko.eif.pi24.dealership_service.schema.*;

public class DealershipEndpointClient extends WebServiceGatewaySupport {
    public final String WS_REQUEST_URI = "http://localhost:8080/ws";

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

//    /**
//     * Get number of dealerships
//     * @return GetDealershipCountResponse
//     */
//    public GetDealershipCountResponse getDealershipCount() {
//        GetDealershipCountRequest request = new GetDealershipCountRequest();
//
//        return (GetDealershipCountResponse)
//                getWebServiceTemplate().marshalSendAndReceive(
//                        WS_REQUEST_URI,
//                        request
//                );
//    }

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
