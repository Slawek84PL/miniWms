package pl.slawek.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.place.Place;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Warehouse {
    private String name;

    private Address address;

    private List<Place> places;
}
