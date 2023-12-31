package pl.slawek.gui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.slawek.domain.warehouse.service.WarehouseService;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final WarehouseService service;

    @GetMapping
    public String homeView(Model model) {
        model.addAttribute("warehouses", service.getAll());
        return "warehouse/home";
    }
}
