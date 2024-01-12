package pl.slawek.gui.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewController {

    @GetMapping
    public String indexView() {
        return "admin/index";
    }
}
