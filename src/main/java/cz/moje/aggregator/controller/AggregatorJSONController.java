package cz.moje.aggregator.controller;

import cz.moje.aggregator.configuration.YAMLConfig;
import cz.moje.aggregator.model.Identification;
import cz.moje.aggregator.sampledata.Data;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AggregatorJSONController {

    private final Data data;
    private Logger logger = LoggerFactory.getLogger(YAMLConfig.class);

    // tady by se mel stat konstruktor injection
    public AggregatorJSONController(Data data) {
        this.data = data;
    }

    @GetMapping(value = "/simple",
            produces = MediaType.ALL_VALUE)
    @Operation(summary = "Simple endpoint.",
        description = "Simple endpoint which return just String text.",
        tags = {"JSON"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
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

    @PostMapping(value = "/createAndReturnJson",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Identification> createAndReturnJSONIdentification(
            @RequestBody
            @Valid
            Identification identification
    ) {
        return ResponseEntity.ok(identification);
    }

    @PostMapping(value = "/returnVoid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> returnVoid(
            @RequestBody String test
    ) {
        logger.info(test);
        return ResponseEntity.ok(test);
    }
}
