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
import pl.slawek.domain.warehouse.entity.Warehouse;
import pl.slawek.domain.warehouse.service.WarehouseService;

import static pl.slawek.gui.admin.AdminViewUtils.*;

@Controller
@RequestMapping("admin/warehouses")
public class WarehouseAdminViewController {

    private final WarehouseService service;

    public WarehouseAdminViewController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("warehouses", service.getAll());
        return "admin/warehouse/index";
    }

    @PostMapping("save")
    public String add(@Valid @ModelAttribute("warehouse") Warehouse warehouse,
                      BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            if (warehouse.getId() != null) {
                warehouse.setAddress(service.getOne(warehouse.getId()).getAddress());
            }
            service.add(warehouse);
            model.addAttribute("warehouse", warehouse);
        }

        addAddres(model, warehouse);

        return "admin/warehouse/edit";
    }

    @GetMapping("edit/{warehouseId}")
    public String edit(@PathVariable long warehouseId, Model model) {
        Warehouse warehouse = service.getOne(warehouseId);
        model.addAttribute("warehouse", warehouse);

        model.addAttribute("type", "WAREHOUSE");
        if (!model.containsAttribute("address")) {
            addAddres(model, warehouse);
        }

        return "admin/warehouse/edit";
    }

}
