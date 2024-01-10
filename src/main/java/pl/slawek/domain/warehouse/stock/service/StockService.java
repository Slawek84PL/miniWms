package pl.slawek.domain.warehouse.stock.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.company.article.service.ArticleService;
import pl.slawek.domain.warehouse.place.service.PlacesService;
import pl.slawek.domain.warehouse.stock.Stock;
import pl.slawek.domain.warehouse.stock.repository.StockRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository repository;
    private final ArticleService articleService;
    private final PlacesService placesService;

    public List<Stock> getAll() {
        return repository.findAll();
    }

    public Stock getOne(long stockId) {
        return repository.findById(stockId).orElseThrow(() -> new EntityNotFoundException("Stock not found for id: " + stockId));
    }

    public List<Stock> getAllStockForArticle(long articleId) {
        return repository.getAllByArticleId(articleId);
    }

    public Stock add(long articleId, long placeId) {
        Stock stock = new Stock();
        stock.setArticle(articleService.getOne(articleId));
        stock.setPlace(placesService.getOne(placeId));

        return repository.save(stock);
    }
}
