package odfd.com.msscbeerservice.repositories;

import odfd.com.msscbeerservice.domain.Beer;
import odfd.com.msscbeerservice.web.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

    Page<Beer> findAllByName(String name, Pageable pageable);

    Page<Beer> findAllByStyle(BeerStyle style, Pageable pageable);

    Page<Beer> findAllByNameAndStyle(String name, BeerStyle style, Pageable pageable);

}
