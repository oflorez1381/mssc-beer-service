package odfd.com.brewery.model.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import odfd.com.brewery.model.BeerDTO;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -5781515597148163111L;

    private BeerDTO beerDTO;
}
