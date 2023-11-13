package pl.slawek.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.slawek.domain.warehouse.Warehouse;
import pl.slawek.domain.warehouse.service.WarehouseService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/warehouse")
public class WarehouseApi {

    private final WarehouseService service;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getWarehouse() {
        return new ResponseEntity<>(service.getWarehouses(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(@Valid @RequestBody Warehouse warehouse) {
        service.addWarehouse(warehouse);
        return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Warehouse warehouse) {
        service.delete(warehouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
