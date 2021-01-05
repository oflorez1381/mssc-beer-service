package odfd.com.msscbeerservice.services.inventory;

import lombok.RequiredArgsConstructor;
import odfd.com.msscbeerservice.services.inventory.model.BeerInventoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient{

    private final InventoryFailoverFeignClient failoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDTO>> getOnhandInventory(UUID beerId) {
        return failoverFeignClient.getOnhandInventory();
    }
}
