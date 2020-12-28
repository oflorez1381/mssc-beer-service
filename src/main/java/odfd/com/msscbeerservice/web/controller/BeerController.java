package odfd.com.msscbeerservice.web.controller;

import lombok.RequiredArgsConstructor;
import odfd.com.msscbeerservice.services.BeerService;
import odfd.com.brewery.model.BeerDTO;
import odfd.com.brewery.model.BeerPagedList;
import odfd.com.brewery.model.BeerStyle;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(path = "beer", produces = { "application/json" })
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyle beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand){

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @GetMapping("beer/{id}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID id,
                                               @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand) {
        return new ResponseEntity<>(beerService.getById(id, showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping(path = "beer")
    public ResponseEntity<BeerDTO> saveNewBeer(@RequestBody @Valid BeerDTO beer) {
        return new ResponseEntity(beerService.saveNewBeer(beer), HttpStatus.CREATED);
    }

    @PutMapping("beer/{id}")
    public ResponseEntity<BeerDTO> updateBeerById(@PathVariable UUID id, @RequestBody @Valid BeerDTO beer) {
        return new ResponseEntity(beerService.updateBeer(id, beer), HttpStatus.NO_CONTENT);
    }

    @GetMapping("beerUpc/{upc}")
    public ResponseEntity<BeerDTO> getBeerByUpc(@PathVariable("upc") String upc) {
        return new ResponseEntity<>(beerService.getByUpc(upc), HttpStatus.OK);
    }
}
