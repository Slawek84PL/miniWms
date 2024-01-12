package pl.slawek.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.domain.delivery.entity.Position;
import pl.slawek.domain.delivery.service.PositionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/position/{deliveryId}")
public class PositionApi {

    private final PositionService service;

    @PostMapping
    public ResponseEntity<Position> add(@PathVariable long deliveryId,
                                        @RequestParam(name = "articleId") long articleId,
                                        @RequestParam(name = "placeId") long placeId) {
        return new ResponseEntity<>(service.add(deliveryId, articleId, placeId), HttpStatus.CREATED);
    }
}
