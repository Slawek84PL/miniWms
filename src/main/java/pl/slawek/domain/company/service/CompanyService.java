package pl.slawek.domain.company.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.address.service.AddressService;
import pl.slawek.domain.company.Company;
import pl.slawek.domain.company.repository.CompanyRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository repository;
    private final AddressService addressService;

    public List<Company> getAll() {
        return repository.findAll();
    }

    public Company getOne(long companyId) {
        return repository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company not found for id: " + companyId));
    }

    public Company add(Company company) {
        return repository.save(company);
    }

    public void delete(long companyId) {
        repository.deleteById(companyId);
    }

    public Company setAddress(long companyId, long addressId) {
        Company company = getOne(companyId);
        Address address = addressService.getOne(addressId);
        company.setAddress(address);
        return repository.save(company);
    }

    public Company updateCompany(long companyId, Company updatedCompany) {

        Company company = getOne(companyId);

        if(updatedCompany.getShortName() != null) {
            company.setShortName(updatedCompany.getShortName());
        }

        if(updatedCompany.getName() != null) {
            company.setName(updatedCompany.getName());
        }

        return repository.save(company);
    }
}
