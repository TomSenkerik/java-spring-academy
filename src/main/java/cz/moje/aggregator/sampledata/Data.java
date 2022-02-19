package cz.moje.aggregator.sampledata;

import cz.moje.aggregator.model.*;
import cz.moje.aggregator.modelXML.XMLIdentification;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Data {
    public String getStringData() {
        return "ahoj";
    }

    public Identification getTestData() {
        Address a = new Address("test address", "65535", "test city");
        Person p = new Person("Test", "Mock");
        List<Device> devices = new LinkedList<Device>();
        devices.add(new Device("teplomer", DeviceType.INDUSTRIAL));
        devices.add(new Device("tlakomer", DeviceType.INDOOR));

        return new Identification("moje Test meteostanice", a, p, devices);
    }

    public Identification getJSONData() {
        Address a = new Address("Na dobre", "13", "Zlin");
        Person p = new Person("Pepa", "Modry");
        List<Device> devices = new LinkedList<>();
        devices.add(new Device("teplomer", DeviceType.INDUSTRIAL));
        devices.add(new Device("tlakomer", DeviceType.INDOOR));

        return new Identification("moje JSON meteostanice", a, p, devices);
    }

    public XMLIdentification getXMLData() {
        Address a = new Address("Moravska", "22", "Breclav");
        Person p = new Person("Josef", "Fistron");
        List<Device> devices = new LinkedList<>();
        devices.add(new Device("teplomer", DeviceType.INDUSTRIAL));
        devices.add(new Device("tlakomer", DeviceType.INDOOR));

        return new XMLIdentification("moje XML meteostanice", a, p, devices);
    }
}
