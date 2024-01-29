package pl.slawek.gui.admin;

import org.springframework.ui.Model;
import pl.slawek.domain.BaseCompanyData;
import pl.slawek.domain.address.Address;

class AdminViewUtils {

    static void addAddres(Model model, BaseCompanyData warehouse) {
        if (warehouse.getAddress() == null) {
            model.addAttribute("address", new Address());
        } else {
            model.addAttribute("address", warehouse.getAddress());
        }
    }
}