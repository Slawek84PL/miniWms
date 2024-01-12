package pl.slawek.domain.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.company.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
