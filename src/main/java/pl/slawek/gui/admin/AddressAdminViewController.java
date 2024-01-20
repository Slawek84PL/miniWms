package pl.slawek.gui.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.address.service.AddressService;

@Controller
@RequestMapping("admin/address")
public class AddressAdminViewController {

   private final AddressService addressService;

    public AddressAdminViewController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("addresses", addressService.getAll());
        return "admin/address/index";
    }

    @GetMapping("add")
    public String addView(Model model) {
        model.addAttribute("address", new Address());
        return "admin/address/add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("address") Address address) {
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
