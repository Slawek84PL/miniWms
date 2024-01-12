package pl.slawek.domain.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.warehouse.entity.Place;
import pl.slawek.domain.warehouse.entity.Warehouse;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAllByWarehouse_Id(long warehouseId);

    @Query("SELECT distinct p FROM Place p WHERE p.id = :placeId AND p.warehouse = :warehouseId")
    Optional<Place> getOneForWarehouse(long placeId, Warehouse warehouseId);

    void deletePlaceByIdAndWarehouse_Id(long placeId, long warehouseId);

    void deletePlaceByWarehouse_Id(long warehouseId);
}
