package odfd.com.msscbeerservice.web.controller;

import odfd.com.msscbeerservice.web.model.BeerDTO;
import odfd.com.msscbeerservice.web.model.BeerStyle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping("/{id}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID id) {
        //todo: implementation
        return new ResponseEntity<>(BeerDTO.builder()
                .id(UUID.randomUUID())
                .name("Beer")
                .style(BeerStyle.ALE)
                .price(new BigDecimal(2.99))
                .upc(123L)
                .version(1)
                .quantityOnHand(1)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Valid BeerDTO beer) {
        //todo: implementation
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBeerById(@PathVariable UUID id, @RequestBody @Valid BeerDTO beer) {
        //todo: implementation
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
