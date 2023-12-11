package pl.slawek.domain.stock.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.article.service.ArticleService;
import pl.slawek.domain.place.service.PlacesService;
import pl.slawek.domain.stock.Stock;
import pl.slawek.domain.stock.repository.StockRepository;

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

    public Stock add(long articelId, long placeId) {
        Stock stock = new Stock();
        stock.setArticle(articleService.getOne(articelId));
        stock.setPlace(placesService.getOne(placeId));

        return repository.save(stock);
    }
}
