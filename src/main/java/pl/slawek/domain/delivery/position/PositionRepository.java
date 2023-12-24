package pl.slawek.domain.delivery.position;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findAllByDelivery_Id(long deliveryId);
}