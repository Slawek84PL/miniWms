package pl.slawek.gui.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.slawek.domain.company.service.CompanyService;

@Controller
@RequestMapping("admin/companies")
public class CompanyAdminViewController {

    private final CompanyService companyService;

    public CompanyAdminViewController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("companies", companyService.getAll());
        return "admin/company/index";
    }
}
