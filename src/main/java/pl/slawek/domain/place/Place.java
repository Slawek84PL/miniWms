package pl.slawek.domain.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slawek.domain.warehouse.Warehouse;
import pl.slawek.domain.article.Article;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Place {

    private String name;

    private PlaceType placeType;

    private List<Article> articles;

    private Warehouse warehouse;
}