package pl.slawek.domain.stock.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.stock.Stock;

import java.util.List;

@Repository
public interface StockRepository extends ListCrudRepository<Stock, Long> {

    @Override
    @Query("select s from Stock s join fetch s.place join fetch s.article")
    List<Stock> findAll();

    @Query("select s from Stock s join fetch s.place join fetch s.article where s.article.id = :articleId")
    List<Stock> getAllByArticleId(long articleId);
}
