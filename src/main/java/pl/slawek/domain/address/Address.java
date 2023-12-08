package pl.slawek.domain.address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String country;
    private String postCode;
    private String city;
    private String street;
    private int number;

}
