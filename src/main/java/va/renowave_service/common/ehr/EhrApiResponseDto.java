package va.renowave_service.common.ehr;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EhrApiResponseDto<T> {

    private T data;
    private int envelopeVersion;
    private int errcode;
    private String message;
    private int status;

}
