package odfd.com.msscbeerservice.web.mappers;

import odfd.com.msscbeerservice.domain.Beer;
import odfd.com.brewery.model.BeerDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDTO beerToBeerDTO(Beer beer);
    BeerDTO beerToBeerDTOWithInventory(Beer beer);
    Beer beerDTOToBeer(BeerDTO dto);
}
