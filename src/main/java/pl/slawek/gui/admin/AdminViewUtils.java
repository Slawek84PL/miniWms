package pl.slawek.gui.admin;

import org.springframework.ui.Model;
import pl.slawek.config.CompanyType;
import pl.slawek.domain.BaseCompanyData;
import pl.slawek.domain.address.Address;

class AdminViewUtils {

    static void addAddress(Model model, BaseCompanyData baseCompanyData, CompanyType type) {

        model.addAttribute("type", type);

        if (baseCompanyData.getAddress() == null) {
            model.addAttribute("address", new Address());
        } else {
            model.addAttribute("address", baseCompanyData.getAddress());
        }
    }

    static String getUrl(CompanyType type, long id) {

        switch (type) {
            case COMPANY -> {
                return "redirect:/admin/companies/edit/" + id;
            }
            case WAREHOUSE -> {
                return "redirect:/admin/warehouses/edit/" + id;
            }
        }
        return "redirect:/admin/address";
    }
}