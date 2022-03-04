package cz.moje.aggregator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.moje.aggregator.configuration.JSONConfig;
import cz.moje.aggregator.sampledata.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AggregatorJSONControllerTest {

    @Autowired
    private JSONConfig jsonConfig;

    @Autowired
    private Data data;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getJson() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/json-info")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(this.data.getJSONData().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.street")
                        .value(this.data.getJSONData().getAddress().getStreet()));
    }

    @Test
    public void postJson() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/createAndReturnJson")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(this.data.getJSONData()))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(this.data.getJSONData().getName()));
    }

    @Test
    public void stackTest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/createAndReturnJson")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(this.data.getJSONData()))
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        this.jsonConfig.convertPOJOtoJSON(
                                Optional.of(this.data.getJSONData())
                        ).orElse(null))
                );
    }
}
