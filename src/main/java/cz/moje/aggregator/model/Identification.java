package cz.moje.aggregator.model;

import lombok.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@Validated
public class Identification {

    @NotNull(message = "not null")
    @NotEmpty(message = "not empty")
    @NotBlank(message = "Identification name is mandatory.")
    String name;

    Address address;

    Person person;

    List<Device> devices;
}
