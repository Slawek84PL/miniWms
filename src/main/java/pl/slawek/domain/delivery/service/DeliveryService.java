package pl.slawek.domain.delivery.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.company.service.CompanyService;
import pl.slawek.domain.delivery.Delivery;
import pl.slawek.domain.delivery.DeliveryDto;
import pl.slawek.domain.delivery.repository.DeliveryRepository;
import pl.slawek.domain.delivery.position.PositionRepository;
import pl.slawek.domain.warehouse.service.WarehouseService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository repository;
    private final WarehouseService warehouseService;
    private final CompanyService companyService;

    public Delivery add(long warehouseId, long companyId) {
        Delivery delivery = new Delivery();
        delivery.setWarehouse(warehouseService.getOne(warehouseId));
        delivery.setCompany(companyService.getOne(companyId));
        return repository.save(delivery);
    }

    public DeliveryDto getAllDeliveryForWarehouse(long warehouseId) {
        List<Delivery> deliveries = repository.findDeliveriesByWarehouse_Id(warehouseId);

        if(deliveries.isEmpty()) {
            throw new EntityNotFoundException("Not found deliveries for warehouse id: " + warehouseId);
        }

        return new DeliveryDto(deliveries);
    }

    public DeliveryDto getAllForWarehouseAndCompany(long warehouseId, long companyId) {
        List<Delivery> deliveries = repository.findByWarehouse_IdAndCompany_Id(warehouseId, companyId);

        if(deliveries.isEmpty()) {
            throw new EntityNotFoundException("Not found deliveries for warehouse id: " + warehouseId);
        }

        return new DeliveryDto(deliveries);
    }

    public Delivery getOneDeliveryForWarehouse(long warehouseId, long deliveryId) {
        return repository.findDeliveryByIdAndWarehouse_Id(deliveryId, warehouseId);
    }
}
