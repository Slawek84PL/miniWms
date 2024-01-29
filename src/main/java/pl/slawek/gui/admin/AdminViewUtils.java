package pl.slawek.gui.admin;

import org.springframework.ui.Model;
import pl.slawek.config.CompanyType;
import pl.slawek.domain.BaseCompanyData;
import pl.slawek.domain.address.Address;

class AdminViewUtils {

    static void addAddres(Model model, BaseCompanyData baseCompanyData, CompanyType type) {

        model.addAttribute("type", type);

        if (baseCompanyData.getAddress() == null) {
            model.addAttribute("address", new Address());
        } else {
            model.addAttribute("address", baseCompanyData.getAddress());
        }
    }
}