package odfd.com.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import odfd.com.msscbeerservice.domain.Beer;
import odfd.com.msscbeerservice.repositories.BeerRepository;
import odfd.com.msscbeerservice.web.controller.NotFoundException;
import odfd.com.msscbeerservice.web.mappers.BeerMapper;
import odfd.com.msscbeerservice.web.model.BeerDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DefaultBeerService implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDTO getById(UUID id) {
        return beerMapper.beerToBeerDTO(
                beerRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO dto) {
        return beerMapper.beerToBeerDTO(beerRepository.save(beerMapper.beerDTOToBeer(dto)));
    }

    @Override
    public BeerDTO updateBeer(UUID id, BeerDTO dto) {
        Beer beer = beerRepository.findById(id).orElseThrow(NotFoundException::new);

        beer.setName(dto.getName());
        beer.setStyle(dto.getStyle().name());
        beer.setPrice(dto.getPrice());
        beer.setUpc(dto.getUpc());

        return beerMapper.beerToBeerDTO(beerRepository.save(beer));
    }
}
