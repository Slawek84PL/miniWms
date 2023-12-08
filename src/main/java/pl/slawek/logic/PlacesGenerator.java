package pl.slawek.logic;

import lombok.AllArgsConstructor;
import pl.slawek.domain.place.Place;
import pl.slawek.domain.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PlacesGenerator {

    private int row;
    private boolean rowAsLetter;
    private int level;
    private int column;

    public List<Place> generate(Warehouse warehouse) {
        List<Place> places = new ArrayList<>();

        for (int i = 1; i <= row; i++) {
            for (int j = 0; j <= level; j++) {
                for (int k = 1; k <= column; k++) {
                    places.add(new Place(null, String.format("%02d-%02d-%02d", i, j, k), warehouse));
                }
            }

        }

        return places;
    }


}
