package pl.slawek.domain.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.stock.Stock;

import java.util.List;

@Repository
public interface StockRepository extends ListCrudRepository<Stock, Long> {
    List<Stock> getAllByArticleId(long articleId);
}
