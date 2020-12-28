package odfd.com.msscbeerservice.repositories;

import odfd.com.msscbeerservice.domain.Beer;
import odfd.com.brewery.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    Page<Beer> findAllByName(String name, Pageable pageable);

    Page<Beer> findAllByStyle(BeerStyle style, Pageable pageable);

    Page<Beer> findAllByNameAndStyle(String name, BeerStyle style, Pageable pageable);

    Beer findByUpc(String upc);

}
