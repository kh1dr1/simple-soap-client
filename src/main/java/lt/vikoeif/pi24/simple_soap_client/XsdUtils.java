package lt.vikoeif.pi24.simple_soap_client;

import lt.viko.eif.pi24.dealership_service.schema.*;

public final class XsdUtils {

    /**
     * Convert a Car to a compact, single-line string
     * @param xsdCar XSD Car POJO
     * @return Car string
     */
    public static String carToString(Car xsdCar) {
        return String.format("""
                ID: %d
                Brand: %s
                Model: %s
                Year: %d
                """,
                xsdCar.getId(),
                xsdCar.getBrand(),
                xsdCar.getModel(),
                xsdCar.getYear()
        );
    }

    /**
     * Convert a Dealership to a verbose, multi-line string
     * @param xsdDealership XSD Dealership POJO
     * @return Dealership string
     */
    public static String dealershipToString(Dealership xsdDealership) {
        return String.format("""
                ID: %d
                Name: %s
                Location: %s
                Phone: %s
                """,
                xsdDealership.getId(),
                xsdDealership.getName(),
                xsdDealership.getLocation(),
                xsdDealership.getPhone()
        );
    }
}
