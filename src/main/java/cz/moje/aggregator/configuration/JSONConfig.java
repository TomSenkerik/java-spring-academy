package cz.moje.aggregator.configuration;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JSONConfig {
    private final ObjectMapper om;
    private Logger logger = LoggerFactory.getLogger(JSONConfig.class);

    public JSONConfig() {
        this.om = new ObjectMapper(new JsonFactory());
        this.om.findAndRegisterModules();
    }

    public Optional<String> convertPOJOtoJSON(Optional<Object> o) {
        try {
            if (o.isPresent()) {
                return Optional.of(this.om.writeValueAsString(o.get()));
            } else {
                return null;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
