package cz.moje.aggregator.controller;

import cz.moje.aggregator.configuration.YAMLConfig;
import cz.moje.aggregator.model.Identification;
import cz.moje.aggregator.sampledata.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AggregatorYAMLController {

    private final Data data;
    private final YAMLConfig yamlConfig;

    public AggregatorYAMLController(Data data, YAMLConfig yamlConfig) {
        this.data = data;
        this.yamlConfig = yamlConfig;
    }

    @GetMapping("/yaml-info")
    public ResponseEntity<String> yamlInfo() {
        Optional<String> result = this.yamlConfig.convertPOJOtoYAML(this.data.getTestData());
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.ok("fail");
        }
    }

    @PostMapping(value = "/createYAMLIdentification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Identification> createYAMLIdentification(
            @RequestBody String inputYAML
    ) {
        Optional<Identification> identification = this.yamlConfig.convertYAMLtoPOJO(inputYAML);
        if (identification.isPresent()) {
            return ResponseEntity.ok(identification.get());
        } else {
            return ResponseEntity.of(null);
        }
    }
}
