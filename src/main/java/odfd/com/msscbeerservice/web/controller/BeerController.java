package odfd.com.msscbeerservice.web.controller;

import lombok.RequiredArgsConstructor;
import odfd.com.msscbeerservice.services.BeerService;
import odfd.com.msscbeerservice.web.model.BeerDTO;
import odfd.com.msscbeerservice.web.model.BeerStyle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{id}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID id) {
        return new ResponseEntity<>(beerService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> saveNewBeer(@RequestBody @Valid BeerDTO beer) {
        return new ResponseEntity(beerService.saveNewBeer(beer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeerDTO> updateBeerById(@PathVariable UUID id, @RequestBody @Valid BeerDTO beer) {
        return new ResponseEntity(beerService.updateBeer(id, beer), HttpStatus.NO_CONTENT);
    }
}
