package pl.slawek.domain.article;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.EAN;
import pl.slawek.domain.company.Company;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @EAN
    @Column(unique = true, nullable = false)
    private String ean;

    @NotNull
    private String name;

    @Getter(AccessLevel.PACKAGE)
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "company_id")
    private Company company;

}
