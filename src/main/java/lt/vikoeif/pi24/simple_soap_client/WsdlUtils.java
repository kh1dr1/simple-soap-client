package lt.vikoeif.pi24.simple_soap_client;

import lt.viko.eif.pi24.dealership_service.schema.*;

public final class WsdlUtils {

    /**
     * Convert a Car to a compact, single-line string
     * @param car XSD Car POJO
     * @return Car string
     */
    public static String carToString(Car car) {
        return "Car { " + "ID: " +
                car.getId() +
                "; Name: " +
                car.getBrand() + " " +
                car.getModel() + " " +
                car.getYear() +
                " }";
    }

    /**
     * Convert a Dealership to a verbose, multi-line string
     * @param xsdDealership XSD Dealership POJO
     * @return Dealership string
     */
    public static String dealershipToString(Dealership xsdDealership) {
        return "Dealership {\n" +
                "  ID: " + xsdDealership.getId() + ";\n" +
                "  Name: " + xsdDealership.getName() + ";\n" +
                "  Location: " + xsdDealership.getLocation() + ";\n" +
                "  Phone: " + xsdDealership.getPhone() + ";\n" +
                "}";
    }
}
