package pl.slawek.gui.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.slawek.domain.warehouse.service.WarehouseService;

@Controller
@RequestMapping("admin/warehouses")
public class WarehouseAdminViewController {

    private final WarehouseService warehouseService;

    public WarehouseAdminViewController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("warehouses", warehouseService.getAll());
        return "admin/warehouse/index";
    }
}
