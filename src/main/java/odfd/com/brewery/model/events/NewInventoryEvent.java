package odfd.com.brewery.model.events;

import odfd.com.brewery.model.BeerDTO;

public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
