package pl.slawek.domain;

import pl.slawek.domain.address.Address;
import pl.slawek.domain.place.Place;

import java.util.List;

public class Warehouse {
    private String name;

    private Address address;

    private List<Place> places;

    public Warehouse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }
}
