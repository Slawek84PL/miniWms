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
import pl.slawek.config.CompanyType;
import pl.slawek.domain.warehouse.entity.Warehouse;
import pl.slawek.domain.warehouse.service.WarehouseService;

import static pl.slawek.gui.admin.AdminViewUtils.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("admin/warehouses")
public class WarehouseAdminViewController {

    public static final String ADMIN_WAREHOUSE_EDIT = "admin/warehouse/edit";
    public static final String WAREHOUSE = "warehouse";
    private final WarehouseService service;

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("warehouses", service.getAll());
        return "admin/warehouse/index";
    }

    @GetMapping("add")
    public String addView(Model model) {
        Warehouse warehouse = new Warehouse();
        model.addAttribute(WAREHOUSE, warehouse);
        addAddress(model, warehouse, CompanyType.WAREHOUSE);
        return ADMIN_WAREHOUSE_EDIT;
    }

    @PostMapping("save")
    public String add(@Valid @ModelAttribute(WAREHOUSE) Warehouse warehouse,
                      BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            if (warehouse.getId() != null) {
                warehouse.setAddress(service.getOne(warehouse.getId()).getAddress());
            }
            service.add(warehouse);
            model.addAttribute(WAREHOUSE, warehouse);
        }

        addAddress(model, warehouse, CompanyType.WAREHOUSE);

        return ADMIN_WAREHOUSE_EDIT;
    }

    @GetMapping("delete/{warehouseId}")
    public String delete(@PathVariable long warehouseId) {
        service.delete(warehouseId);
        return "redirect:/admin/warehouses";
    }

    @GetMapping("edit/{warehouseId}")
    public String edit(@PathVariable long warehouseId, Model model) {
        Warehouse warehouse = service.getOne(warehouseId);
        model.addAttribute(WAREHOUSE, warehouse);

        if (!model.containsAttribute("address")) {
            addAddress(model, warehouse, CompanyType.WAREHOUSE);
        }

        return ADMIN_WAREHOUSE_EDIT;
    }

}
