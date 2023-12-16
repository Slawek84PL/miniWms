package pl.slawek.domain.place.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slawek.domain.place.Place;
import pl.slawek.domain.place.repository.PlaceRepository;
import pl.slawek.domain.warehouse.Warehouse;
import pl.slawek.domain.warehouse.service.WarehouseService;
import pl.slawek.logic.PlacesGenerator;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlacesService {

    private final PlaceRepository repository;
    private final WarehouseService warehouseService;

    public List<Place> getAll() {
        return repository.findAll();
    }

    public Place getOne(long placeId) {
        return repository.findById(placeId).orElseThrow(() -> new EntityNotFoundException("Place not found for id: " + placeId));
    }

    public Place getOne(long warehouseId, long placeId) {
        return repository.getOneForWarehouse(placeId, warehouseService.getOne(warehouseId)).orElseThrow(() -> new EntityNotFoundException("Place not found for id: " + placeId));
    }

    @Transactional
    public Place add(long warehouseId, Place place) {
        place.setWarehouse(warehouseService.getOne(warehouseId));
        return repository.save(place);
    }

    @Transactional
    public void generatePlaces(long warehouseId, PlacesGenerator placesGenerator) {
        Warehouse warehouse = warehouseService.getOne(warehouseId);
        repository.saveAll(placesGenerator.generate(warehouse));
    }

    public void delete(long placeId) {
        repository.deleteById(placeId);
    }
}
