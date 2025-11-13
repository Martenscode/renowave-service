package va.renowave_service.service.cron;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import va.renowave_service.enums.Kov;
import va.renowave_service.service.EhitiseNaitajadService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ScheduledApartmentCountService {

    private final EhitiseNaitajadService ehitiseNaitajadService;

    @Scheduled(cron = "0 0 0 1 * *")
    public void totalAptCountReq() {
        fetchAndSaveApartmentBuildings(Kov.getAllCodes());
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void antslaValdAptCountReq() {
        fetchAndSaveApartmentBuildings(List.of(Kov.ANTSLA_VALD.getCode()));
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void rougeValdAptCountReq() {
        fetchAndSaveApartmentBuildings(List.of(Kov.ROUGE_VALD.getCode()));
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void setomaaValdAptCountReq() {
        fetchAndSaveApartmentBuildings(List.of(Kov.SETOMAA_VALD.getCode()));
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void voruLinnAptCountReq() {
        fetchAndSaveApartmentBuildings(List.of(Kov.VORU_LINN.getCode()));
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void voruValdAptCountReq() {
        fetchAndSaveApartmentBuildings(List.of(Kov.VORU_VALD.getCode()));
    }

    private void fetchAndSaveApartmentBuildings(List<String> kovCodes) {
        log.info("Starting monthly apartments count API call for KOV codes: {}", kovCodes);
        try {
            ehitiseNaitajadService.fetchAndSaveApartmentBuildings(kovCodes);
            log.info("Apartments count API call successful for KOV codes: {}", kovCodes);
        } catch (Exception e) {
            log.error("Exception occurred when sending monthly apartments count request for KOV codes {}: {}",
                    kovCodes, e.getMessage(), e);
        }
    }

}
