package pl.slawek.gui.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.address.service.AddressService;
import pl.slawek.domain.company.service.CompanyService;

@Controller
@RequestMapping("admin/address")
public class AddressAdminViewController {

    private final AddressService addressService;
    private final CompanyService companyService;

    public AddressAdminViewController(AddressService addressService, CompanyService companyService) {
        this.addressService = addressService;
        this.companyService = companyService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("addresses", addressService.getAll());
        return "admin/address/index";
    }

    @GetMapping("add")
    public String addView(Model model) {
        model.addAttribute("address", new Address());
        return "admin/address/edit";
    }

    @PostMapping("save")
    public String add(@Valid @ModelAttribute("address") Address address,
                      BindingResult bindingResult,
                      Model model,
                      RedirectAttributes redirectAttributes,
                      @RequestParam("type") String type,
                      @RequestParam("typeId") Long id) {

        if (type.equals("COMPANY")) {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.address", bindingResult); // TODO: 2024-01-27 flashattribute tutaj trzeba użyć
                redirectAttributes.addFlashAttribute("address", address);
                return "redirect:/admin/companies/edit/" + id;
            }
            addressService.add(address);
            companyService.setAddress(id, address.getId());
            return "redirect:/admin/companies/edit/" + id;
        }

        addressService.add(address);
        return "redirect:/admin/address";
    }

    @GetMapping("delete/{addressId}")
    public String delete(@PathVariable long addressId) {
        addressService.delete(addressId);
        return "redirect:/admin/address";
    }

    @GetMapping("edit/{addressId}")
    public String edit(@PathVariable long addressId, Model model) {
        model.addAttribute("address", addressService.getOne(addressId));
        return "admin/address/edit";
    }
}