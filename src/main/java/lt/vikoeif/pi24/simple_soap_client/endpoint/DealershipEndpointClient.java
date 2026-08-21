package lt.vikoeif.pi24.simple_soap_client.endpoint;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import lt.viko.eif.pi24.dealership_service.schema.*;

public class DealershipEndpointClient extends WebServiceGatewaySupport {
    public final String WS_REQUEST_URI = "http://localhost:8080/ws";

    public GetDealershipByIdResponse getDealershipById(int id) {
        GetDealershipByIdRequest request = new GetDealershipByIdRequest();
        request.setId(id);

        return (GetDealershipByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(WS_REQUEST_URI, request);
    }

    /**
     * Send a SOAP request to get a list of all Dealerships in the database
     * @return {@code GetAllDealershipsResponse} with a List of Dealerships
     */
    public GetAllDealershipsResponse getAllDealerships() {
        GetAllDealershipsRequest request = new GetAllDealershipsRequest();

        return (GetAllDealershipsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(WS_REQUEST_URI, request);
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
     * Send a SOAP request to insert a Dealership into the database
     * @param xsdDealership XSD Dealership POJO
     * @return {@code AddDealershipResponse} with a boolean success flag
     */
    public AddDealershipResponse addDealership(Dealership xsdDealership) {
        AddDealershipRequest request = new AddDealershipRequest();
        request.setDealership(xsdDealership);

        return (AddDealershipResponse) getWebServiceTemplate()
                .marshalSendAndReceive(WS_REQUEST_URI, request);
    }

    /**
     * Get all Cars of a single Dealership.
     * @param id Dealership ID
     * @return {@code List<Car>}
     */
    public GetDealershipCarsResponse getDealershipCars(int id) {
        GetDealershipCarsRequest request = new GetDealershipCarsRequest();
        request.setDealershipId(id);

        return (GetDealershipCarsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(WS_REQUEST_URI, request);
    }
}
