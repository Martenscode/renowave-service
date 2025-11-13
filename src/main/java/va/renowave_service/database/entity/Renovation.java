package va.renowave_service.database.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "renovation")
public class Renovation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "year")
    private Integer year;

    @NotNull
    @Column(name = "antsla_vald")
    private Integer antslaVald;

    @NotNull
    @Column(name = "rouge_vald")
    private Integer rougeVald;

    @NotNull
    @Column(name = "setomaa_vald")
    private Integer setomaaVald;

    @NotNull
    @Column(name = "voru_linn")
    private Integer voruLinn;

    @NotNull
    @Column(name = "voru_vald")
    private Integer voruVald;

    @NotNull
    @Column(name = "county_total")
    private Integer countyTotal;

}
