package cz.moje.aggregator.controller;

import cz.moje.aggregator.model.Identification;
import cz.moje.aggregator.sampledata.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregatorJSONController {

    private final Data data;

    // tady by se mel stat konstruktor injection
    public AggregatorJSONController(Data data) {
        this.data = data;
    }

    @GetMapping(value = "/simple",
            produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> simpleWelcome() {
        return ResponseEntity.ok(data.getStringData());
    }

    @GetMapping(value = "/json-info",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Identification> jsonInfo() {
        return ResponseEntity.ok(data.getJSONData());
    }

    @PostMapping(value = "/createJSONIdentification",
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Identification> createJSONIdentification(
        @RequestBody Identification identification
    ) {
        return ResponseEntity.ok(identification);
    }
}
