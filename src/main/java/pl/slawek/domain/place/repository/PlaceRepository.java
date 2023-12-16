package pl.slawek.domain.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.place.Place;
import pl.slawek.domain.warehouse.Warehouse;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT distinct p FROM Place p WHERE p.id = :placeId AND p.warehouse = :warehouseId")
    Optional<Place> getOneForWarehouse(long placeId, Warehouse warehouseId);
}
