package odfd.com.brewery.model.events;

import odfd.com.brewery.model.BeerDTO;

public class BrewBeerEvent extends BeerEvent{

    public BrewBeerEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
