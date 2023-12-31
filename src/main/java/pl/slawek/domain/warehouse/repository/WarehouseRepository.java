package pl.slawek.domain.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.warehouse.Warehouse;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query("select w from Warehouse w join fetch w.address join fetch w.places")
    List<Warehouse> findAllWarehouseWithAddressAndPlaces();
}
