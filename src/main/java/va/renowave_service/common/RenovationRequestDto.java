package va.renowave_service.common;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RenovationRequestDto {

    private Integer id;
    @NotNull
    private Integer year;
    @NotNull
    private Integer antslaVald;
    @NotNull
    private Integer rougeVald;
    @NotNull
    private Integer setomaaVald;
    @NotNull
    private Integer voruLinn;
    @NotNull
    private Integer voruVald;
    @NotNull
    private Integer countyTotal;

}
