package cz.moje.aggregator.model;

import lombok.Value;

@Value
public class Address {
    String street;
    String number;
    String city;
}
