package pl.slawek.domain.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slawek.domain.delivery.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}