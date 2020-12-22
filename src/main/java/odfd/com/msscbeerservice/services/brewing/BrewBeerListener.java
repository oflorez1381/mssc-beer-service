package odfd.com.msscbeerservice.services.brewing;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import odfd.com.msscbeerservice.config.JmsConfig;
import odfd.com.msscbeerservice.domain.Beer;
import odfd.com.msscbeerservice.events.BrewBeerEvent;
import odfd.com.msscbeerservice.events.NewInventoryEvent;
import odfd.com.msscbeerservice.repositories.BeerRepository;
import odfd.com.msscbeerservice.web.model.BeerDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(BrewBeerEvent event) {

        BeerDTO beerDTO = event.getBeerDTO();

        Beer beer = beerRepository.getOne(beerDTO.getId());

        beerDTO.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDTO);

        log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDTO.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }


}
