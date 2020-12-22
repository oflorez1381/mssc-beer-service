package odfd.com.msscbeerservice.events;

import lombok.NoArgsConstructor;
import odfd.com.msscbeerservice.web.model.BeerDTO;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent{

    public BrewBeerEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
