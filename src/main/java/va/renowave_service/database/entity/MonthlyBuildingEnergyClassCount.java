package va.renowave_service.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(
        name = "monthly_building_energy_class_count",
        uniqueConstraints = @UniqueConstraint(columnNames = {"year", "municipality", "energy_class", "month"})
)
public class MonthlyBuildingEnergyClassCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "year")
    private Integer year;

    @NotNull
    @Column(name = "municipality", length = 100)
    private String municipality;

    @NotNull
    @Column(name = "energy_class", length = 10)
    private String energyClass;

    @NotNull
    @Column(name = "count")
    private Integer count;

    @NotNull
    @Column(name = "month")
    private LocalDate month;

    @NotNull
    @Column(name = "date_added")
    private LocalDateTime dateAdded = LocalDateTime.now();

}
