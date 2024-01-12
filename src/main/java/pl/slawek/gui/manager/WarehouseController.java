package pl.slawek.gui.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.slawek.domain.delivery.service.DeliveryService;
import pl.slawek.domain.warehouse.entity.Warehouse;
import pl.slawek.domain.warehouse.service.WarehouseService;

@RequiredArgsConstructor
@Controller
@RequestMapping("manager")
public class WarehouseController {

    private static final String BASE_URL = "manager/warehouse";

    private final WarehouseService service;
    private final DeliveryService deliveryService;

    @GetMapping
    public String homeView(Model model) {
        model.addAttribute("warehouses", service.getAll());
        return BASE_URL + "/home";
    }

    @GetMapping("{warehouseId}")
    public String singleHome(@PathVariable long warehouseId, Model model) {
        Warehouse warehouse = service.getOne(warehouseId);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("places", warehouse.getPlaces());
        model.addAttribute("deliveries", deliveryService.getAllForWarehouse(warehouseId));
        return BASE_URL +  "/singleHome";
    }

    @GetMapping("{warehouseId}/{deliveryId}")
    public String getDeliveryDetails(@PathVariable long warehouseId, @PathVariable long deliveryId, Model model) {
        Warehouse warehouse = service.getOne(warehouseId);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("places", warehouse.getPlaces());
        model.addAttribute("deliveries", deliveryService.getAllForWarehouse(warehouseId));
        model.addAttribute("positions", deliveryService.getOneDeliveryForWarehouse(warehouseId, deliveryId));
        return BASE_URL +  "/singleHome";
    }


}
