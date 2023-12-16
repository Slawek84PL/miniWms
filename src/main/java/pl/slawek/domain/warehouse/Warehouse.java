package pl.slawek.domain.warehouse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slawek.domain.address.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 4)
    @Column(unique = true)
    private String shortName;

    @Size(min = 3, max = 25)
    private String name;

    @OneToOne
    private Address address;

    @PrePersist
    private void afterCreate() {
        upperShortName();
    }

    @PreUpdate
    private void afterUpdate() {
        upperShortName();
    }

    private void upperShortName() {
        this.shortName = shortName.toUpperCase();
    }

}
