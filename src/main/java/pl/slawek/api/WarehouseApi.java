package pl.slawek.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.domain.Warehouse;
import pl.slawek.domain.adress.Adress;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseApi {

    @GetMapping
    public ResponseEntity<Warehouse> getWarehouse() {
        Adress adress = new Adress("PL", "Warszowice", "Strit", 10);
        Warehouse warehouse = new Warehouse("Warszowice");
        warehouse.setAdress(adress);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

}
