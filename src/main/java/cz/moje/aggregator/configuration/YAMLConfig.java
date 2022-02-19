package cz.moje.aggregator.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import cz.moje.aggregator.model.Identification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

// neni to tak uplne konfigurace :)
@Component
public class YAMLConfig {

    private final ObjectMapper om;
    private Logger logger = LoggerFactory.getLogger(YAMLConfig.class);

    public YAMLConfig() {
        om = new ObjectMapper(new YAMLFactory());
        om.findAndRegisterModules();
    }

    public Optional<String> convertPOJOtoYAML(Object o) {
        try {
            return Optional.of(this.om.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            logger.error("fail converting POJO to YAML");
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Identification> convertYAMLtoPOJO(String yaml) {
        try {
            return Optional.of(this.om.readValue(yaml, Identification.class));
        } catch (JsonProcessingException e) {
            logger.error("fail converting YAML to POJO");
            e.printStackTrace();
        }
        return null;
    }
}
