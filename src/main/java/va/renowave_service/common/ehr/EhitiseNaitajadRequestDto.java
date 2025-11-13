package va.renowave_service.common.ehr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EhitiseNaitajadRequestDto {

    @JsonProperty("algus_aasta")
    private int algusAasta;
    @JsonProperty("ehitise_seisund")
    private List<String> ehitiseSeisund;
    private List<String> kaos;
    private List<String> kov;
    @JsonProperty("lopp_aasta")
    private int loppAasta;
    @JsonProperty("otsi_aastata")
    private boolean otsiAastata;
    @JsonProperty("otsi_kovita")
    private boolean otsiKovita;
    @JsonProperty("tase_sisu")
    private List<String> taseSisu;

}
