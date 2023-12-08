package pl.slawek.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.domain.place.Place;
import pl.slawek.domain.place.service.PlacesService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/places")
public class PlaceApi {

    private final PlacesService service;

    @GetMapping
    public ResponseEntity<List<Place>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Place> getOne(@PathVariable long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @PostMapping("{warehouseId}")
    public ResponseEntity<Place> add(@PathVariable long warehouseId, @Valid @RequestBody Place place) {
        return new ResponseEntity<>(service.add(warehouseId, place), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}