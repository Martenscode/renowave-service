package va.renowave_service.common.ehr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EhitiseEnergeetikaRequestDto {

    @JsonProperty("algus_aasta")
    private int algusAasta;
    @JsonProperty("algus_aasta2")
    private int algusAasta2;
    @JsonProperty("ehitise_seisund")
    private List<String> ehitiseSeisund;
    @JsonProperty("energia_tyyp")
    private List<String> energiaTyyp;
    private List<String> kaos;
    private List<String> kov;
    @JsonProperty("lopp_aasta")
    private int loppAasta;
    @JsonProperty("lopp_aasta2")
    private int loppAasta2;
    @JsonProperty("otsi_aastata")
    private boolean otsiAastata;
    @JsonProperty("tase_sisu")
    private List<String> taseSisu;

}
