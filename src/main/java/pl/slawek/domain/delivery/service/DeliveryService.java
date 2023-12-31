package pl.slawek.domain.delivery.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.company.service.CompanyService;
import pl.slawek.domain.delivery.Delivery;
import pl.slawek.domain.delivery.DeliveryDto;
import pl.slawek.domain.delivery.repository.DeliveryRepository;
import pl.slawek.domain.warehouse.service.WarehouseService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository repository;
    private final WarehouseService warehouseService;
    private final CompanyService companyService;

    public DeliveryDto add(long warehouseId, long companyId) {
        Delivery delivery = new Delivery();
        delivery.setWarehouse(warehouseService.getOne(warehouseId));
        delivery.setCompany(companyService.getOne(companyId));
        return new DeliveryDto(repository.save(delivery));
    }

    public List<DeliveryDto> getAllForWarehouseAndCompany(long warehouseId, long companyId) {
        List<Delivery> deliveries = repository.findByWarehouse_IdAndCompany_Id(warehouseId, companyId);

        if(deliveries.isEmpty()) {
            throw new EntityNotFoundException("Not found deliveries for warehouse id: " + warehouseId);
        }

        return deliveries.stream().map(DeliveryDto::new).toList();
    }

    public DeliveryDto getOneDeliveryForWarehouse(long warehouseId, long deliveryId) {
        return new DeliveryDto(repository.findDeliveryByIdAndWarehouse_Id(deliveryId, warehouseId));
    }

    public List<DeliveryDto> getAllForWarehouse(long warehouseId) {
        return repository.findDeliveriesByWarehouse_Id(warehouseId).stream().map(DeliveryDto::new).toList();
    }

    public Delivery getOne(long deliveryId) {
        return repository.findById(deliveryId).orElseThrow(() -> new EntityNotFoundException("Delivery not found for id: " + deliveryId));
    }
}
