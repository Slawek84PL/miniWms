package pl.slawek.domain.warehouse;

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
public class Warehouse {

    @Size(min = 3, max = 4)
    private String shortName;

    @Size(min = 3, max = 25)
    private String name;

    private Address address;
}
