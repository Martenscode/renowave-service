package va.renowave_service.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "apartment_statistics")
public class ApartmentStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "kov_name", unique = true)
    private String kovName;

    @NotNull
    @Column(name = "kahe_kolme")
    private Integer kaheKolme;

    @NotNull
    @Column(name = "kuni_12")
    private Integer kuni12;

    @NotNull
    @Column(name = "rohkem_kui_12")
    private Integer rohkemKui12;

    @NotNull
    @Column(name = "eriliselt_suur")
    private Integer eriliseltSuur;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
    
}
