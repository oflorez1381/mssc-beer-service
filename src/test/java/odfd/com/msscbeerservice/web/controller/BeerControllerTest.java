package odfd.com.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import odfd.com.msscbeerservice.bootstrap.BeerLoader;
import odfd.com.msscbeerservice.domain.Beer;
import odfd.com.msscbeerservice.repositories.BeerRepository;
import odfd.com.msscbeerservice.services.BeerService;
import odfd.com.msscbeerservice.web.model.BeerDTO;
import odfd.com.msscbeerservice.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
//@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev", uriPort = 8081)
@AutoConfigureRestDocs(uriPort = 8081)
@WebMvcTest
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {

        given(beerService.getById(any())).willReturn(getValidBeerDTO());

        ConstrainedFields fields = new ConstrainedFields(BeerDTO.class);

        mockMvc.perform(get("/api/v1/beer/{id}", UUID.randomUUID().toString())
                .param("iscold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer-get",
                        pathParameters(
                                parameterWithName("id").description("UUID of desired beer to get.")
                        ),
                        requestParameters(
                                parameterWithName("iscold").description("Is Beer Cold query param")
                        ),
                        responseFields(
                                fields.withPath("id").description("Id of Beer").type(UUID.class),
                                fields.withPath("version").description("Version number"),
                                fields.withPath("createdDate").description("Date Created").type(OffsetDateTime.class),
                                fields.withPath("lastModifiedDate").description("Date Updated").type(OffsetDateTime.class),
                                fields.withPath("name").description("Beer Name"),
                                fields.withPath("style").description("Beer Style"),
                                fields.withPath("upc").description("UPC of Beer"),
                                fields.withPath("price").description("Price"),
                                fields.withPath("quantityOnHand").description("Quantity On hand")
                        )
                ));
    }


    @Test
    void saveNewBeer() throws Exception {
        BeerDTO beer = getValidBeerDTO();
        String beerToJson = objectMapper.writeValueAsString(beer);
        ConstrainedFields fields = new ConstrainedFields(BeerDTO.class);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDTO());

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer-new",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("name").description("Name of the beer"),
                                fields.withPath("style").description("Style of Beer"),
                                fields.withPath("upc").description("Beer UPC").attributes(),
                                fields.withPath("price").description("Beer Price"),
                                fields.withPath("quantityOnHand").ignored()
                        ))
                );
    }

    @Test
    void updateBeerById() throws Exception {
        given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDTO());
        BeerDTO beer = getValidBeerDTO();
        String beerToJson = objectMapper.writeValueAsString(beer);

        mockMvc.perform(put(String.format("/api/v1/beer/%s", UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isNoContent());
    }

    BeerDTO getValidBeerDTO() {
        return BeerDTO.builder()
                .name("Beer")
                .style(BeerStyle.ALE)
                .price(new BigDecimal(2.99))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }

    private class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}