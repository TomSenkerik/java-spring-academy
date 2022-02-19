package cz.moje.aggregator.controller;

import cz.moje.aggregator.modelXML.XMLIdentification;
import cz.moje.aggregator.sampledata.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregatorXMLController {

    private final Data data;

    // tady by se mel stat konstruktor injection
    public AggregatorXMLController(Data data) {
        this.data = data;
    }

    @GetMapping(value = "/xml-info", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<XMLIdentification> xmlInfo() {
        return ResponseEntity.ok(data.getXMLData());
    }

    @PostMapping(value = "/createXMLIdentification",
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<XMLIdentification> createXMLIdentification(
            @RequestBody XMLIdentification xmlIdentification
    ) {
        return ResponseEntity.ok(xmlIdentification);
    }
}
