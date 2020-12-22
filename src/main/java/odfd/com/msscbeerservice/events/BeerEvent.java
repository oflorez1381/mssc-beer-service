package odfd.com.msscbeerservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import odfd.com.msscbeerservice.web.model.BeerDTO;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -5781515597148163111L;

    private BeerDTO beerDTO;
}
