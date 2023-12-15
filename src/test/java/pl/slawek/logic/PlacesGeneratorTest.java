package pl.slawek.logic;

import org.junit.jupiter.api.Test;
import pl.slawek.domain.place.Place;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlacesGeneratorTest {

    @Test
    void ShouldGenerateOnePlace() {
//        when
        PlacesGenerator placesGenerator = new PlacesGenerator(1, 1, false, 0, 0, 1, 1);
//        given
        List<Place> list = getPlaceList(placesGenerator);
//        then
        assertThat(list.size()).isEqualTo(1);
        assertEquals(list.get(0).getName(), "01-00-01");

    }

    @Test
    void ShouldGenerateTwoPlaces() {
//        when
        PlacesGenerator placesGenerator = new PlacesGenerator(1, 1, false, 0, 0, 1, 2);
//        given
        List<Place> list = getPlaceList(placesGenerator);
//        then
        assertThat(list.size()).isEqualTo(2);
        assertEquals(list.get(0).getName(), "01-00-01");
        assertEquals(list.get(1).getName(), "01-00-02");
    }

    @Test
    void ShouldGenerate10Rows() {
//        when
        PlacesGenerator placesGenerator = new PlacesGenerator(1, 10, false, 0, 0, 1, 1);
//        given
        List<Place> list = getPlaceList(placesGenerator);
//        then
        assertThat(list.size()).isEqualTo(10);
        assertEquals(list.get(0).getName(), "01-00-01");
        assertEquals(list.get(9).getName(), "10-00-01");
    }

    @Test
    void ShouldGenerate10Levels() {
//        when
        PlacesGenerator placesGenerator = new PlacesGenerator(1, 1, false, 0, 9, 1, 1);
//        given
        List<Place> list = getPlaceList(placesGenerator);
//        then
        assertThat(list.size()).isEqualTo(10);
        assertEquals(list.get(0).getName(), "01-00-01");
        assertEquals(list.get(9).getName(), "01-09-01");
    }

    @Test
    void ShouldGenerate10Places() {
//        when
        PlacesGenerator placesGenerator = new PlacesGenerator(1, 1,false, 0, 0, 1, 10);
//        given
        List<Place> list = getPlaceList(placesGenerator);
//        then
        assertThat(list.size()).isEqualTo(10);
        assertEquals(list.get(0).getName(), "01-00-01");
        assertEquals(list.get(9).getName(), "01-00-10");
    }

    private List<Place> getPlaceList(PlacesGenerator placesGenerator) {
        return placesGenerator.generate();
    }

    @Test
    void shouldCalculateListSize() {
        PlacesGenerator placesGenerator = new PlacesGenerator(1, 1, false, 0, 0, 1, 1);

    }
}