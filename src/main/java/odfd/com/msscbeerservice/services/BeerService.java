package odfd.com.msscbeerservice.services;

import odfd.com.msscbeerservice.web.model.BeerDTO;

import java.util.UUID;

public interface BeerService {

    BeerDTO getById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    BeerDTO updateBeer(UUID id, BeerDTO beer);
}
