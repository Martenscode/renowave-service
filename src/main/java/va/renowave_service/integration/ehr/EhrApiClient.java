package va.renowave_service.integration.ehr;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import va.renowave_service.common.ehr.EhitiseEnergeetikaRequestDto;
import va.renowave_service.common.ehr.EhitiseEnergeetikaResponseDto;
import va.renowave_service.common.ehr.EhitiseNaitajadRequestDto;
import va.renowave_service.common.ehr.EhitiseNaitajadResponseDto;
import va.renowave_service.config.integration.EhrApiConfig;

@Component
@AllArgsConstructor
public class EhrApiClient {

    private final RestTemplate restTemplate;
    private final EhrApiConfig ehrApiConfig;

    private static final String EHITISE_NAITAJAD_ENDPOINT = "/reports/ehitise_naitajad_i";
    private static final String EHITISE_ENERGEETIKA_ENDPOINT = "/reports/ehitise_energeetika_i";

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000, multiplier = 2)
    )
    public EhitiseNaitajadResponseDto fetchEhitiseNaitajad(EhitiseNaitajadRequestDto request) {
        String url = ehrApiConfig.getStatistikaportaalBaseUrl() + EHITISE_NAITAJAD_ENDPOINT;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EhitiseNaitajadRequestDto> entity = new HttpEntity<>(request, headers);

        ResponseEntity<EhitiseNaitajadResponseDto> response =
                restTemplate.postForEntity(url, entity, EhitiseNaitajadResponseDto.class);

        return response.getBody();
    }

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000, multiplier = 2)
    )
    public EhitiseEnergeetikaResponseDto fetchEhitiseEnergeetika(EhitiseEnergeetikaRequestDto request) {
        String url = ehrApiConfig.getStatistikaportaalBaseUrl() + EHITISE_ENERGEETIKA_ENDPOINT;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EhitiseEnergeetikaRequestDto> entity = new HttpEntity<>(request, headers);

        ResponseEntity<EhitiseEnergeetikaResponseDto> response =
                restTemplate.postForEntity(url, entity, EhitiseEnergeetikaResponseDto.class);

        return response.getBody();
    }

}
