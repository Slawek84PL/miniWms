package pl.slawek.domain.warehouse.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slawek.domain.warehouse.Place;
import pl.slawek.domain.warehouse.repository.PlaceRepository;
import pl.slawek.domain.warehouse.Warehouse;
import pl.slawek.logic.PlacesGenerator;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlacesService {

    private final PlaceRepository repository;
    private final WarehouseService warehouseService;

    @Transactional(readOnly = true)
    public List<Place> getAll(long warehouseId) {
        return repository.findAllByWarehouse_Id(warehouseId);
    }

    @Transactional(readOnly = true)
    public Place getOne(long placeId) {
        return repository.findById(placeId).orElseThrow(() -> new EntityNotFoundException("Place not found for id: " + placeId));
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public void delete(long warehouseId, long placeId) {
        repository.deletePlaceByIdAndWarehouse_Id(placeId, warehouseId);
    }

    @Transactional
    public void deleteAllPlaces(long warehouseId) {
        repository.deletePlaceByWarehouse_Id(warehouseId);
    }
}
