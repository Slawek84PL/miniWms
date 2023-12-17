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
import pl.slawek.logic.PlacesGenerator;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/{warehouseId}/places")
public class PlaceApi {

    private final PlacesService service;

    @GetMapping
    public ResponseEntity<List<Place>> getAll(@PathVariable long warehouseId) {
        return new ResponseEntity<>(service.getAll(warehouseId), HttpStatus.OK);
    }

    @GetMapping("{placeId}")
    public ResponseEntity<Place> getOne(@PathVariable long warehouseId, @PathVariable long placeId) {
        return new ResponseEntity<>(service.getOne(warehouseId, placeId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Place> add(@PathVariable long warehouseId, @Valid @RequestBody Place place) {
        return new ResponseEntity<>(service.add(warehouseId, place), HttpStatus.CREATED);
    }

    @PostMapping("generateplaces")
    public ResponseEntity<?> generatePlaces(@PathVariable long warehouseId, @Valid @RequestBody PlacesGenerator placesGenerator) {
        service.generatePlaces(warehouseId, placesGenerator);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable long warehouseId) {
        service.deleteAllPlaces(warehouseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{placeId}")
    public ResponseEntity<?> delete(@PathVariable long warehouseId, @PathVariable long placeId) {
        service.delete(warehouseId, placeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
