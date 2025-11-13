package va.renowave_service.common.ehr;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class EhitiseEnergeetikaResponseDto extends EhrApiResponseDto<List<EhitiseEnergeetikaResponseDto.EhitiseEnergeetikaData>> {

    public EhitiseEnergeetikaResponseDto(List<EhitiseEnergeetikaData> data, int envelopeVersion, int errcode, String message, int status) {
        super(data, envelopeVersion, errcode, message, status);
    }

    @AllArgsConstructor
    @Data
    public static class EhitiseEnergeetikaData {
        private int ehitisi;
        private String energiaklass;
        private String grupp;
        private int valjastatud;
    }
}
