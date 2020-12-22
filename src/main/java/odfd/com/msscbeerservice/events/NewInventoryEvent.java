package odfd.com.msscbeerservice.events;

import odfd.com.msscbeerservice.web.model.BeerDTO;

public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
