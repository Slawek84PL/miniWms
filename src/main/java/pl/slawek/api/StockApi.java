package pl.slawek.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.domain.warehouse.entity.Stock;
import pl.slawek.domain.warehouse.service.StockService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stock")
public class StockApi {

    private final StockService service;

    @GetMapping
    public ResponseEntity<List<Stock>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Stock> getOne(@PathVariable long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/articel/{articleId}")
    public ResponseEntity<List<Stock>> getAllStockForArticle(@PathVariable long articleId) {
        return new ResponseEntity<>(service.getAllStockForArticle(articleId), HttpStatus.OK);
    }

    @PostMapping("{articleId}/{placeId}")
    public ResponseEntity<Stock> add(@PathVariable long articleId, @PathVariable long placeId) {
        return new ResponseEntity<>(service.add(articleId, placeId), HttpStatus.CREATED);
    }
}
