package odfd.com.msscbeerservice.events;

import lombok.NoArgsConstructor;
import odfd.com.msscbeerservice.web.model.BeerDTO;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
