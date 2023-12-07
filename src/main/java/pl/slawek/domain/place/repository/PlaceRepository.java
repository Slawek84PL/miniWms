package pl.slawek.domain.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.place.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
