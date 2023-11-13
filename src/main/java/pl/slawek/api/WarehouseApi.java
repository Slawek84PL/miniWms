package pl.slawek.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.domain.Warehouse;
import pl.slawek.domain.address.Address;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseApi {

    @GetMapping
    public ResponseEntity<Warehouse> getWarehouse() {
        Address address = new Address("PL", "Warszowice", "Strit", 10);
        Warehouse warehouse = new Warehouse("Warszowice");
        warehouse.setAdress(address);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

}
