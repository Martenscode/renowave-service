package va.renowave_service.service.cron;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import va.renowave_service.enums.Kov;
import va.renowave_service.service.EhitiseEnergeetikaService;
import va.renowave_service.service.EhitiseNaitajadService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ScheduledEnergyStatisticsCountService {

    private final EhitiseEnergeetikaService ehitiseEnergeetikaService;

    @Scheduled(cron = "0 0 0 1 * *")
    public void totalEnergyClassCountReq() {
        fetchAndSaveEnergyStatistics(Kov.getAllCodes());
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void antslaValdEnergyClassCountReq() {
        fetchAndSaveEnergyStatistics(List.of(Kov.ANTSLA_VALD.getCode()));
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void rougeValdEnergyClassCountReq() {
        fetchAndSaveEnergyStatistics(List.of(Kov.ROUGE_VALD.getCode()));
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void setomaaValdEnergyClassCountReq() {
        fetchAndSaveEnergyStatistics(List.of(Kov.SETOMAA_VALD.getCode()));
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void voruLinnEnergyClassCountReq() {
        fetchAndSaveEnergyStatistics(List.of(Kov.VORU_LINN.getCode()));
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void voruValdEnergyClassCountReq() {
        fetchAndSaveEnergyStatistics(List.of(Kov.VORU_VALD.getCode()));
    }

    private void fetchAndSaveEnergyStatistics(List<String> kovCodes) {
        log.info("Starting monthly energy statistics API call for KOV codes: {}", kovCodes);
        try {
            ehitiseEnergeetikaService.fetchAndSaveEnergyStatistics(kovCodes);
            log.info("Energy statistics API call successful for KOV codes: {}", kovCodes);
        } catch (Exception e) {
            log.error("Exception occurred when sending monthly energy statistics request for KOV codes {}: {}",
                    kovCodes, e.getMessage(), e);
        }
    }

}
