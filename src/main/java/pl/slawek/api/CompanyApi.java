package pl.slawek.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.domain.company.entity.Company;
import pl.slawek.domain.company.service.CompanyService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/company")
public class CompanyApi {

    private final CompanyService service;

    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{companyId}")
    public ResponseEntity<Company> getOne(@PathVariable long companyId) {
        return new ResponseEntity<>(service.getOne(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> add(@Valid @RequestBody Company company) {
        return new ResponseEntity<>(service.add(company), HttpStatus.CREATED);
    }

    @PatchMapping("{companyId}")
    public ResponseEntity<Company> updateWarehouse(@PathVariable long companyId,
                                                     @Valid @RequestBody Company updatedCompany) {
        return new ResponseEntity<>(service.updateCompany(companyId, updatedCompany), HttpStatus.OK);

    }

    @PutMapping("/setaddress/{companyId}/{addressId}")
    public ResponseEntity<Company> setAddress(@PathVariable long companyId, @PathVariable long addressId) {
        return new ResponseEntity<>(service.setAddress(companyId, addressId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
