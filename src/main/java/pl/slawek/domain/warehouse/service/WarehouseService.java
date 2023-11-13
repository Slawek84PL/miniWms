package pl.slawek.domain.warehouse.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.slawek.domain.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Service
public class WarehouseService {

    private List<Warehouse> warehouses = new ArrayList<>();

    public Warehouse addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
        return warehouse;
    }

}
