package pl.slawek.domain.warehouse.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.address.repository.AddressRepository;
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
        warehouse.setName(warehouse.getShortName().toUpperCase());
        return repository.save(warehouse);
    }

    public void delete(long warehouseId) {
        repository.deleteById(warehouseId);
    }

    public Warehouse setAddress(long warehouseId, long addressId) {
        Warehouse warehouse = repository.findById(warehouseId).orElseThrow(() -> new EntityNotFoundException("Warehouse not found for id: " + warehouseId));
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("Address not found for id: " + addressId));
        Address address = addressService.getOne(addressId);
        warehouse.setAddress(address);
        return repository.save(warehouse);
    }
}
