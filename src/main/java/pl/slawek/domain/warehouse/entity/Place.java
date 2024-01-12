package pl.slawek.domain.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "place", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "warehouse_id"})
})
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 15)
    private String name;

    @Min(1)
    @Max(99)
    private int row;

    @Min(0)
    @Max(99)
    private int level;

    @Min(1)
    @Max(99)
    private int place;

    @Getter(AccessLevel.PACKAGE)
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
}