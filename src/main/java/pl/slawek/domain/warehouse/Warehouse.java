package pl.slawek.domain.warehouse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slawek.domain.BaseCompanyData;
import pl.slawek.domain.warehouse.place.Place;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Warehouse extends BaseCompanyData {

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.REMOVE)
    private List<Place> places;
}
