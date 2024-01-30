package pl.slawek.gui.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import pl.slawek.config.CompanyType;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.address.service.AddressService;

import static pl.slawek.gui.admin.AdminViewUtils.getUrl;

@RequiredArgsConstructor
@Controller
@RequestMapping("admin/address")
public class AddressAdminViewController {

    public static final String ADDRESS = "address";
    private final AddressService addressService;
    private final AddressAdminViewUtils addressAdminViewUtils;

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("addresses", addressService.getAll());
        return "admin/address/index";
    }

    @GetMapping("add")
    public String addView(Model model) {
        model.addAttribute(ADDRESS, new Address());
        return "admin/address/edit";
    }

    @PostMapping("save")
    public String add(@Valid @ModelAttribute(ADDRESS) Address address,
                      BindingResult bindingResult, Model model,
                      RedirectAttributes redirectAttributes,
                      @RequestParam("type") CompanyType type,
                      @RequestParam("typeId") Long id) {

        String redirectUrl = getUrl(type, id);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.address", bindingResult);
            redirectAttributes.addFlashAttribute(ADDRESS, address);
            return redirectUrl;
        }

        if (address.getId() == null) {
            addressService.add(address);
        }

        addressAdminViewUtils.addAddress(address, type, id);
        return redirectUrl;
    }

    @GetMapping("delete/{addressId}")
    public String delete(@PathVariable long addressId) {
        addressService.delete(addressId);
        return "redirect:/admin/address";
    }

    @GetMapping("edit/{addressId}")
    public String edit(@PathVariable long addressId, Model model) {
        model.addAttribute(ADDRESS, addressService.getOne(addressId));
        return "admin/address/edit";
    }
}