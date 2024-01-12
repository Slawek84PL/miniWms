package pl.slawek.domain.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slawek.domain.BaseCompanyData;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Company extends BaseCompanyData {

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Article> articles;

}
