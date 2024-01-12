package pl.slawek.logic;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import pl.slawek.domain.warehouse.entity.Place;
import pl.slawek.domain.warehouse.entity.Warehouse;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class PlacesGenerator {

    @Min(1)
    @Max(99)
    private int rowFrom;

    @Min(1)
    @Max(99)
    private int rowTo;

    private boolean rowAsLetter;

    @Min(0)
    @Max(99)
    private int levelFrom;

    @Min(0)
    @Max(99)
    private int levelTo;

    @Min(1)
    @Max(99)
    private int columnFrom;

    @Min(1)
    @Max(99)
    private int columnTo;

    public List<Place> generate(Warehouse warehouse) {
        List<Place> places = new LinkedList<>();

        for (int row = rowFrom; row <= rowTo; row++) {

            for (int level = levelFrom; level <= levelTo; level++) {

                for (int column = columnFrom; column <= columnTo; column++) {
                    places.add(new Place(null, String.format("%02d-%02d-%02d", row, level, column), warehouse));
                }
            }
        }

        return places;
    }

}
