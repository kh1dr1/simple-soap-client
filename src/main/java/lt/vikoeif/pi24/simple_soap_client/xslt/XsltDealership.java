package lt.vikoeif.pi24.simple_soap_client.xslt;

import jakarta.xml.bind.annotation.*;

import lt.viko.eif.pi24.dealership_service.schema.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Dealership model class but with an extra field: {@code inventory}.
 * <p>
 * Inventory contains a list of all Cars in a Dealership.
 * Use method {@code addCarToInventory} to add a new Car to inventory.
 * Use method {@code getInventory} to get access to inventory.
 * <p>
 * This class is suitable for JAXB marshaling.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dealership", propOrder = {
        "id",
        "name",
        "location",
        "phone",
        "inventory"
})
public class XsltDealership {
    protected int id;

    @XmlElement(required = true)
    protected String name;

    @XmlElement(required = true)
    protected String location;

    @XmlElement(required = true)
    protected String phone;

    // The XmlElement annotation is not strictly needed here. It changes nothing.
    //@XmlElement(namespace = "")
    protected List<Car> inventory;

    public XsltDealership() {
        inventory = new ArrayList<>();
    }

    public XsltDealership(int id, String name, String location, String phone, List<Car> inventory) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Car> getInventory() {
        return inventory;
    }

    public void addCarToInventory(Car car) {
        inventory.add(car);
    }
}
