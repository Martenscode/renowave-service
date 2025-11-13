package va.renowave_service.common.ehr;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class EhitiseNaitajadResponseDto extends EhrApiResponseDto<List<EhitiseNaitajadResponseDto.EhitiseNaitajadData>> {

    public EhitiseNaitajadResponseDto(List<EhitiseNaitajadData> data, int envelopeVersion, int errcode, String message, int status) {
        super(data, envelopeVersion, errcode, message, status);
    }

    @AllArgsConstructor
    @Data
    public static class EhitiseNaitajadData {
        private String ehitiseKumnend;
        private int ehitisi;
        private Double eluruumiPind;
        private String kasutusotstarve;
        private Double mitteeluruumideArv;
        private Double mitteeluruumidePind;
        private Double suletudNetopind;
        private Double tubadeArv;
        private int veerg;
    }
}
