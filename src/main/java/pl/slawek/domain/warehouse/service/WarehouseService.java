package pl.slawek.domain.warehouse.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.address.service.AddressService;
import pl.slawek.domain.warehouse.Warehouse;
import pl.slawek.domain.warehouse.repository.WarehouseRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WarehouseService {

    private final WarehouseRepository repository;
    private final AddressService addressService;

    public List<Warehouse> getAll() {
        return repository.findAll();
    }

    public Warehouse getOne(long warehouseId) {
        return repository.findById(warehouseId).orElseThrow(() -> new EntityNotFoundException("Warehouse not found for id: " + warehouseId));
    }

    public Warehouse add(Warehouse warehouse) {
        warehouse.setShortName(warehouse.getShortName().toUpperCase());
        return repository.save(warehouse);
    }

    public void delete(long warehouseId) {
        repository.deleteById(warehouseId);
    }

    public Warehouse setAddress(long warehouseId, long addressId) {
        Warehouse warehouse = getOne(warehouseId);
        Address address = addressService.getOne(addressId);
        warehouse.setAddress(address);
        return repository.save(warehouse);
    }

    public Warehouse updateWarehouse(long warehouseId, Warehouse updatedWarehouse) {

        Warehouse warehouse = getOne(warehouseId);

        if(updatedWarehouse.getShortName() != null) {
            warehouse.setShortName(updatedWarehouse.getShortName());
        }

        if(updatedWarehouse.getName() != null) {
            warehouse.setName(updatedWarehouse.getName());
        }

        return repository.save(warehouse);
    }
}
