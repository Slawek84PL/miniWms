package pl.slawek.domain.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.stock.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
