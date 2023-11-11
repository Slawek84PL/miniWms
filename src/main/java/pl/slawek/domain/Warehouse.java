package pl.slawek.domain;

import pl.slawek.domain.adress.Adress;
import pl.slawek.domain.place.Place;

import java.util.List;

public class Warehouse {
    private String name;

    private Adress adress;

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

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
