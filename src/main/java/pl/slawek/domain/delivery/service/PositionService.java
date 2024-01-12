package pl.slawek.domain.delivery.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slawek.domain.company.Article;
import pl.slawek.domain.delivery.Delivery;
import pl.slawek.domain.delivery.Position;
import pl.slawek.domain.delivery.repository.PositionRepository;
import pl.slawek.domain.warehouse.Place;

@RequiredArgsConstructor
@Service
public class PositionService {

    private final PositionRepository repository;
    private final DeliveryService deliveryService;

    @Transactional
    public Position add(long deliveryId, long articleId, long placeId) {
        Delivery delivery = deliveryService.getOne(deliveryId);

        Place place = delivery.getWarehouse().getPlaces().stream()
                .filter(p -> p.getId() == placeId)
                .findFirst().orElseThrow(() -> new EntityNotFoundException(String.format("W magazynie %s nie znaleziono miesjca o id %s", delivery.getWarehouse().getName(),  placeId)));

        Article article = delivery.getCompany().getArticles().stream()
                .filter(a -> a.getId() == articleId)
                .findFirst().orElseThrow(() -> new EntityNotFoundException(String.format("Dostawca %s nie może dostarczać artykułu o id %s", delivery.getCompany().getName(),  articleId)));

        Position position = new Position();
        position.setDelivery(delivery);
        position.setArticle(article);
        position.setPlace(place);

        return repository.save(position);
    }
}