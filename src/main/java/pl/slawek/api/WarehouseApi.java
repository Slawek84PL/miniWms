package pl.slawek.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.slawek.domain.warehouse.Warehouse;
import pl.slawek.domain.warehouse.service.WarehouseService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseApi {

    private final WarehouseService service;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Warehouse> getOne(@PathVariable long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Warehouse> add(@Valid @RequestBody Warehouse warehouse) {
        return new ResponseEntity<>(service.add(warehouse), HttpStatus.CREATED);
    }

    @PatchMapping("{warehouseId}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable long warehouseId,
                                                     @RequestBody @NotNull Warehouse updatedWarehouse) {
        return new ResponseEntity<>(service.updateWarehouse(warehouseId, updatedWarehouse), HttpStatus.OK);

    }

    @PutMapping("/setaddress/{warehouseId}/{addressId}")
    public ResponseEntity<Warehouse> setAddress(@PathVariable long warehouseId, @PathVariable long addressId) {
        return new ResponseEntity<>(service.setAddress(warehouseId, addressId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
