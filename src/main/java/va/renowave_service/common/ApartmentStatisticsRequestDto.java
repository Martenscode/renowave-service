package va.renowave_service.common;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApartmentStatisticsRequestDto {

    @NotNull
    private Integer id;
    @NotNull
    private Integer kaheKolme;
    @NotNull
    private Integer kuni12;
    @NotNull
    private Integer rohkemKui12;
    @NotNull
    private Integer eriliseltSuur;

}
