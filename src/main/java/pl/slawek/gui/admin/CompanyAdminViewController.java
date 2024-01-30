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
import pl.slawek.domain.company.entity.Company;
import pl.slawek.domain.company.service.CompanyService;

import static pl.slawek.gui.admin.AdminViewUtils.addAddress;

@RequiredArgsConstructor
@Controller
@RequestMapping("admin/companies")
public class CompanyAdminViewController {

    public static final String ADMIN_COMPANY_EDIT = "admin/company/edit";
    public static final String COMPANY = "company";
    private final CompanyService service;

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("companies", service.getAll());
        return "admin/company/index";
    }

    @GetMapping("add")
    public String addView(Model model) {
        Company company = new Company();
        model.addAttribute(COMPANY, company);
        addAddress(model, company, CompanyType.COMPANY);
        return ADMIN_COMPANY_EDIT;
    }

    @PostMapping("save")
    public String add(@Valid @ModelAttribute(COMPANY) Company company,
                      BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            if (company.getId() != null) {
                company.setAddress(service.getOne(company.getId()).getAddress());
            }
            service.add(company);
            model.addAttribute(COMPANY, company);
        }

        addAddress(model, company, CompanyType.COMPANY);

        return ADMIN_COMPANY_EDIT;
    }

    @GetMapping("delete/{companyId}")
    public String delete(@PathVariable long companyId) {
        service.delete(companyId);
        return "redirect:/admin/companies";
    }

    @GetMapping("edit/{companyId}")
    public String edit(@PathVariable long companyId, Model model) {
        Company company = service.getOne(companyId);
        model.addAttribute(COMPANY, company);

        if (!model.containsAttribute("address")) {
            addAddress(model, company, CompanyType.COMPANY);
        }

        return ADMIN_COMPANY_EDIT;
    }
}
