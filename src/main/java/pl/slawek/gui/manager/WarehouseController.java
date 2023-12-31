package pl.slawek.gui.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.slawek.domain.warehouse.service.WarehouseService;

@RequiredArgsConstructor
@Controller
@RequestMapping("manager")
public class WarehouseController {

    private final WarehouseService service;

    @GetMapping
    public String homeView(Model model) {
        model.addAttribute("warehouses", service.getAll());
        return "manager/warehouse/home";
    }
}
