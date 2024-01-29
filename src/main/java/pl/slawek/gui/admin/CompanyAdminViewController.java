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
import pl.slawek.config.CompanyType;
import pl.slawek.domain.company.entity.Company;
import pl.slawek.domain.company.service.CompanyService;

import static pl.slawek.gui.admin.AdminViewUtils.addAddres;

@Controller
@RequestMapping("admin/companies")
public class CompanyAdminViewController {

    private final CompanyService service;

    public CompanyAdminViewController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("companies", service.getAll());
        return "admin/company/index";
    }

    @GetMapping("add")
    public String addView(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        addAddres(model, company, CompanyType.COMPANY);
        return "admin/company/edit";
    }

    @PostMapping("save")
    public String add(@Valid @ModelAttribute("company") Company company,
                      BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {

            if (company.getId() != null) {
                company.setAddress(service.getOne(company.getId()).getAddress());
            }

            service.add(company);
            model.addAttribute("company", company);
        }

        addAddres(model, company, CompanyType.COMPANY);

        return "admin/company/edit";
    }

    @GetMapping("delete/{companyId}")
    public String delete(@PathVariable long companyId) {
        service.delete(companyId);
        return "redirect:/admin/companies";
    }

    @GetMapping("edit/{companyId}")
    public String edit(@PathVariable long companyId, Model model) {
        Company company = service.getOne(companyId);
        model.addAttribute("company", company);

        if (!model.containsAttribute("address")) {
            addAddres(model, company, CompanyType.COMPANY);
        }

        return "admin/company/edit";
    }
}
