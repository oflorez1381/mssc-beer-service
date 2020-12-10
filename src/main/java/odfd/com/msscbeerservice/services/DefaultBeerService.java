package odfd.com.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import odfd.com.msscbeerservice.domain.Beer;
import odfd.com.msscbeerservice.repositories.BeerRepository;
import odfd.com.msscbeerservice.web.controller.NotFoundException;
import odfd.com.msscbeerservice.web.mappers.BeerMapper;
import odfd.com.msscbeerservice.web.model.BeerDTO;
import odfd.com.msscbeerservice.web.model.BeerPagedList;
import odfd.com.msscbeerservice.web.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.cache.annotation.Cacheable;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultBeerService implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
    @Override
    public BeerDTO getById(UUID id, Boolean showInventoryOnHand) {

        Function<Beer, BeerDTO> mappingFunction = beerMapper::beerToBeerDTO;

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (showInventoryOnHand) {
            mappingFunction = beerMapper::beerToBeerDTOWithInventory;
        }
        return mappingFunction.apply(beerRepository.findById(id).orElseThrow(NotFoundException::new));
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

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public BeerPagedList listBeers(String name, BeerStyle style, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(style)) {
            //search both
            beerPage = beerRepository.findAllByNameAndStyle(name, style, pageRequest);
        } else if (!StringUtils.isEmpty(name) && StringUtils.isEmpty(style)) {
            //search beer_service name
            beerPage = beerRepository.findAllByName(name, pageRequest);
        } else if (StringUtils.isEmpty(name) && !StringUtils.isEmpty(style)) {
            //search beer_service style
            beerPage = beerRepository.findAllByStyle(style, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        Function<Beer, BeerDTO> mappingFunction = beerMapper::beerToBeerDTO;

        if (showInventoryOnHand) {
            mappingFunction = beerMapper::beerToBeerDTOWithInventory;
        }

        beerPagedList = new BeerPagedList(beerPage
                .getContent()
                .stream()
                .map(mappingFunction)
                .collect(Collectors.toList()),
                PageRequest
                        .of(beerPage.getPageable().getPageNumber(),
                                beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;
    }
}
