package pl.slawek.gui.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.slawek.config.CompanyType;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.company.service.CompanyService;
import pl.slawek.domain.warehouse.service.WarehouseService;

@RequiredArgsConstructor
@Component
class AddressAdminViewUtils {

    private final CompanyService companyService;
    private final WarehouseService warehouseService;

    void addAddress(Address address, CompanyType type, Long id) {
        if (type == CompanyType.COMPANY) {
            companyService.setAddress(id, address.getId());
        }

        if (type == CompanyType.WAREHOUSE) {
            warehouseService.setAddress(id, address.getId());
        }
    }
}
