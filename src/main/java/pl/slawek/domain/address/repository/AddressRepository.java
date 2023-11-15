package pl.slawek.domain.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.address.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
