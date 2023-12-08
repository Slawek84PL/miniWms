package pl.slawek.domain.place.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.place.Place;
import pl.slawek.domain.place.repository.PlaceRepository;
import pl.slawek.domain.warehouse.service.WarehouseService;

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

    public Place add(long warehouseId, Place place) {
        place.setWarehouse(warehouseService.getOne(warehouseId));
        return repository.save(place);
    }

    public void delete(long placeId) {
        repository.deleteById(placeId);
    }
}
