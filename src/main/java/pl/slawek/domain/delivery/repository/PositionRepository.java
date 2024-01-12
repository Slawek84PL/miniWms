package pl.slawek.domain.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slawek.domain.delivery.Position;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findAllByDelivery_Id(long deliveryId);
}