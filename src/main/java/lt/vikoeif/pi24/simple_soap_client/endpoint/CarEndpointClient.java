package lt.vikoeif.pi24.simple_soap_client.endpoint;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import lt.viko.eif.pi24.dealership_service.schema.*;

public class CarEndpointClient extends WebServiceGatewaySupport {
    public final String WS_REQUEST_URI = "http://localhost:8080/ws";

    public AddCarResponse addCar(Car xsdCar) {
        AddCarRequest request = new AddCarRequest();
        request.setCar(xsdCar);

        return (AddCarResponse)
                getWebServiceTemplate().marshalSendAndReceive(
                        WS_REQUEST_URI,
                        request
                );
    }

    public GetAllCarsResponse getAllCars() {
        GetAllCarsRequest request = new GetAllCarsRequest();

        return (GetAllCarsResponse)
                getWebServiceTemplate().marshalSendAndReceive(
                        WS_REQUEST_URI,
                        request
                );
    }
}
