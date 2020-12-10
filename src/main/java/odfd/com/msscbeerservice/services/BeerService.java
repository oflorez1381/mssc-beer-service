package odfd.com.msscbeerservice.services;

import odfd.com.msscbeerservice.web.model.BeerDTO;
import odfd.com.msscbeerservice.web.model.BeerPagedList;
import odfd.com.msscbeerservice.web.model.BeerStyle;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerDTO getById(UUID id, Boolean showInventoryOnHand);

    BeerDTO getByUpc(String upc);

    BeerDTO saveNewBeer(BeerDTO beer);

    BeerDTO updateBeer(UUID id, BeerDTO beer);

    BeerPagedList listBeers(String name, BeerStyle style, PageRequest pageRequest, Boolean showInventoryOnHand);
}
