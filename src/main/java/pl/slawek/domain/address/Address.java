package pl.slawek.domain.address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 2, max = 20)
    private String country;

    @NotBlank
    @Size(min = 5, max = 5)
    private String postCode;

    @NotBlank
    @Size(min = 2, max = 25)
    private String city;

    @NotBlank
    @Size(min = 2, max = 50)
    private String street;

    private int number;

}