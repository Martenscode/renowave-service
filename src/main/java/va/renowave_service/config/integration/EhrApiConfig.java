package va.renowave_service.config.integration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class EhrApiConfig {

    @Value("${ehr.statistikaportaal.baseUrl}")
    private String statistikaportaalBaseUrl;

}
