package cz.moje.aggregator.model;

import lombok.Value;

@Value
public class Device {
    private String name;
    private DeviceType deviceType;
}
