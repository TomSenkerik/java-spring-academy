package cz.moje.aggregator.modelXML;

import cz.moje.aggregator.model.Address;
import cz.moje.aggregator.model.Device;
import cz.moje.aggregator.model.Person;
import lombok.Value;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Value
@XmlRootElement
public class XMLIdentification {
    String name;
    Address address;
    Person person;
    List<Device> devices;
}
