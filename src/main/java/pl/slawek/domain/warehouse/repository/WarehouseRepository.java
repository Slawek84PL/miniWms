package pl.slawek.domain.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.warehouse.entity.Warehouse;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query("select w from Warehouse w left join fetch w.address left join fetch w.places")
    List<Warehouse> findAllWarehouseWithAddressAndPlaces();
}
