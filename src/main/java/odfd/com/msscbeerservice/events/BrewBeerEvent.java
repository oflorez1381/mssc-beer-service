package odfd.com.msscbeerservice.events;

import odfd.com.msscbeerservice.web.model.BeerDTO;

public class BrewBeerEvent extends BeerEvent{

    BrewBeerEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
