package pl.slawek.domain.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.slawek.domain.delivery.position.Position;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class DeliveryDto {

    private String warehouse;
    private String companyName;

    private List<List<Position>> positions = new ArrayList<>();
    
    public DeliveryDto(List<Delivery> source) {
        this.warehouse = source.get(0).getWarehouse().getName();
        this.companyName = source.get(0).getCompany().getName();

        for (Delivery delivery : source) {
//            delivery.setPositions(delivery.getPositions());
            this.positions.add(delivery.getPositions());
        }
    }
}
