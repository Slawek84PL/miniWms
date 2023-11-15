package pl.slawek.domain.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.warehouse.Warehouse;
import pl.slawek.domain.warehouse.repository.WarehouseRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WarehouseService {

    private final WarehouseRepository repository;

    public List<Warehouse> getAll() {
        return repository.findAll();
    }

    public Warehouse getOne(long id) {
        return repository.getReferenceById(id);
    }

    public Long add(Warehouse warehouse) {
        warehouse.getShortName().toUpperCase();
        return repository.save(warehouse).getId();
    }

    public void delete(long warehouseId) {
        repository.deleteById(warehouseId);
    }
}
