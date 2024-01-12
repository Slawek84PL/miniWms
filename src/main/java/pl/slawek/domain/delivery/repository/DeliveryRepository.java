package pl.slawek.domain.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.slawek.domain.delivery.entity.Delivery;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("select distinct d from Delivery d where d.warehouse.id =:warehouseId")
    List<Delivery> findDeliveriesByWarehouse_Id(long warehouseId);

    Delivery findDeliveryByIdAndWarehouse_Id(long deliveryId, long warehouseId);

    @Query("select d from Delivery d where d.warehouse.id = ?1 and d.company.id = ?2")
    List<Delivery> findByWarehouse_IdAndCompany_Id(Long warehouseId, Long companyId);
}