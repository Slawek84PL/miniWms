package pl.slawek.domain.delivery.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.slawek.domain.delivery.Delivery;
import pl.slawek.domain.delivery.Position;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class DeliveryDto {

    private long deliveryId;
    private String warehouse;
    private String companyName;

    private List<Position> positions = new ArrayList<>();

    public DeliveryDto(Delivery source) {
        this.deliveryId = source.getId();
        this.warehouse = source.getWarehouse().getName();
        this.companyName = source.getCompany().getName();
        this.positions = source.getPositions();
    }
}
