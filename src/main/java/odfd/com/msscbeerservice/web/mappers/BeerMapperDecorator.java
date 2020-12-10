package odfd.com.msscbeerservice.web.mappers;

import odfd.com.msscbeerservice.domain.Beer;
import odfd.com.msscbeerservice.services.inventory.BeerInventoryService;
import odfd.com.msscbeerservice.web.model.BeerDTO;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {

    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDTO beerToBeerDTO(Beer beer) {
        return mapper.beerToBeerDTO(beer);
    }

    @Override
    public Beer beerDTOToBeer(BeerDTO beerDto) {
        return mapper.beerDTOToBeer(beerDto);
    }

    @Override
    public BeerDTO beerToBeerDTOWithInventory(Beer beer) {
        BeerDTO dto = mapper.beerToBeerDTO(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }
}
