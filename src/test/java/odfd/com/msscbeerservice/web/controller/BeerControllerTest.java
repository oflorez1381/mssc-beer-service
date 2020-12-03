package odfd.com.msscbeerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import odfd.com.msscbeerservice.web.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(String.format("/api/v1/beer/%s", UUID.randomUUID().toString()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDTO beer = BeerDTO.builder().build();
        String beerToJson = objectMapper.writeValueAsString(beer);

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {

        BeerDTO beer = BeerDTO.builder().build();
        String beerToJson = objectMapper.writeValueAsString(beer);

        mockMvc.perform(put(String.format("/api/v1/beer/%s", UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isNoContent());
    }
}