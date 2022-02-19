package cz.moje.aggregator.model;

import lombok.Value;

import java.util.List;

@Value
public class Identification {
    String name;
    Address address;
    Person person;
    List<Device> devices;
}
