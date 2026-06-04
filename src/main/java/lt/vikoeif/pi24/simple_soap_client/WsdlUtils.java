package lt.vikoeif.pi24.simple_soap_client;

import lt.vikoeif.pi24.wsdl.*;

import java.util.List;

public final class WsdlUtils {

    /**
     * Convert a Car to a compact, single-line string
     * @param car WSDL Car object
     * @return String
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
     * Convert a Dealership's Inventory to a verbose, multi-line string
     * @param inventory WSDL Dealership's Inventory object
     * @param indentSpaces the amount of spaces to add at the beginning of each line
     * @return String
     */
    public static String dealershipInventoryToString(
            Dealership.Inventory inventory,
            int indentSpaces
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory {").append("\n");

        List<Car> inventoryCars = inventory.getCar();
        String indent = " ".repeat(indentSpaces);

        for (Car car : inventoryCars) {
            // Double indent for cars list
            sb.append(indent).append(indent).append(carToString(car)).append(";\n");
        }

        // Single indent
        sb.append(indent).append("}");
        return sb.toString();
    }

    /**
     * Convert a Dealership to a verbose, multi-line string
     * @param dealership WSDL Dealership object
     * @return String
     */
    public static String dealershipToString(Dealership dealership) {
        return "Dealership {\n" +
                "  ID: " + dealership.getId() + ";\n" +
                "  Name: " + dealership.getName() + ";\n" +
                "  Location: " + dealership.getLocation() + ";\n" +
                "  Phone: " + dealership.getPhone() + ";\n" +
                "  Inventory: " + dealershipInventoryToString(dealership.getInventory(), 2) + ";\n" +
                "}";
    }
}
