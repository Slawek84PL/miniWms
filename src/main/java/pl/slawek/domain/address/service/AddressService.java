package pl.slawek.domain.address.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.address.Address;
import pl.slawek.domain.address.repository.AddressRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository repository;

    public List<Address> getAll() {
        return repository.findAll();
    }

    public Address getOne(long addressId) {
        return repository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("Address not found for id: " + addressId));
    }

    public Address add(Address address) {
        return repository.save(address);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
