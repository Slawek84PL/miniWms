package pl.slawek.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.domain.delivery.Delivery;
import pl.slawek.domain.delivery.DeliveryDto;
import pl.slawek.domain.delivery.service.DeliveryService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/{warehouseId}/delivery")
public class DeliveryApi {

    private final DeliveryService service;

    @GetMapping
    public ResponseEntity<DeliveryDto> getAll(@PathVariable long warehouseId) {
        return new ResponseEntity<>(service.getAllDeliveryForWarehouse(warehouseId), HttpStatus.OK);
    }

    @GetMapping("{deliveryId}")
    public ResponseEntity<Delivery> getOneDeliveryForWarehouse(@PathVariable long warehouseId, @PathVariable long deliveryId) {
        return new ResponseEntity<>(service.getOneDeliveryForWarehouse(warehouseId, deliveryId), HttpStatus.CREATED);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<DeliveryDto> getAllForWarehouseAndCompany(@PathVariable long warehouseId, @PathVariable long companyId) {
        return new ResponseEntity<>(service.getAllForWarehouseAndCompany(warehouseId, companyId), HttpStatus.CREATED);
    }

    @PostMapping("/company/{companyId}")
    public ResponseEntity<Delivery> add(@PathVariable long warehouseId, @PathVariable long companyId) {
        return new ResponseEntity<>(service.add(warehouseId, companyId), HttpStatus.CREATED);
    }
}
