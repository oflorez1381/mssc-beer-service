package odfd.com.msscbeerservice.web.mappers;

import odfd.com.msscbeerservice.domain.Beer;
import odfd.com.msscbeerservice.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDTO beerToBeerDTO(Beer beer);
    Beer beerDTOToBeer(BeerDTO dto);
}
