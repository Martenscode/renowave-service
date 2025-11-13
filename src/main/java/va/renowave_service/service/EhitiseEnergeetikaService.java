package va.renowave_service.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import va.renowave_service.common.ehr.EhitiseEnergeetikaRequestDto;
import va.renowave_service.common.ehr.EhitiseEnergeetikaResponseDto;
import va.renowave_service.config.EhrConfig;
import va.renowave_service.database.entity.MonthlyBuildingEnergyClassCount;
import va.renowave_service.database.repository.MonthlyBuildingEnergyClassCountRepository;
import va.renowave_service.enums.EnergyClass;
import va.renowave_service.integration.ehr.EhrApiClient;
import va.renowave_service.utils.KovUtil;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class EhitiseEnergeetikaService {

    private final EhrApiClient ehrApiClient;
    private final MonthlyBuildingEnergyClassCountRepository repository;
    private final KovUtil kovUtil = new KovUtil();

    @Transactional
    public void fetchAndSaveEnergyStatistics(List<String> kovs) {
        EhitiseEnergeetikaResponseDto res = ehrApiClient.fetchEhitiseEnergeetika(buildEhitiseEnergeetikaRequest(kovs));
        Map<EnergyClass, Integer> energyClasses = countAllEnergyClasses(res.getData());
        int currentYear = LocalDate.now().getYear();

        for (Map.Entry<EnergyClass, Integer> entry : energyClasses.entrySet()) {
            MonthlyBuildingEnergyClassCount energyClassCount = new MonthlyBuildingEnergyClassCount();
            energyClassCount.setYear(currentYear);
            energyClassCount.setMunicipality(kovUtil.getKovFromCodes(kovs).getName());
            energyClassCount.setEnergyClass(entry.getKey().name());
            energyClassCount.setCount(entry.getValue());
            energyClassCount.setMonth(YearMonth.now().atDay(1));

            try {
                repository.save(energyClassCount);
            } catch (Exception e) {
                log.error("Failed to save energy statistics record for {}", kovs, e);
            }
        }
    }

    private EhitiseEnergeetikaRequestDto buildEhitiseEnergeetikaRequest(List<String> kovs) {
        EhitiseEnergeetikaRequestDto req = new EhitiseEnergeetikaRequestDto();
        req.setAlgusAasta(0);
        req.setAlgusAasta2(0);
        req.setEhitiseSeisund(List.of("Olemas"));
        req.setEnergiaTyyp(List.of("ETA", "KEK"));
        req.setKaos(EhrConfig.DEFAULT_KAOS);
        req.setKov(kovs);
        req.setLoppAasta(2100);
        req.setLoppAasta2(2100);
        req.setOtsiAastata(true);
        req.setTaseSisu(List.of("all"));
        return req;
    }

    private Map<EnergyClass, Integer> countAllEnergyClasses(List<EhitiseEnergeetikaResponseDto.EhitiseEnergeetikaData> buildings) {
        Map<EnergyClass, Integer> energyTotals = new EnumMap<>(EnergyClass.class);
        for (EnergyClass energyClass : EnergyClass.values()) {
            energyTotals.put(energyClass, 0);
        }

        for (EhitiseEnergeetikaResponseDto.EhitiseEnergeetikaData building : buildings) {
            if (building.getEnergiaklass() == null) continue;

            try {
                EnergyClass energyClass = EnergyClass.valueOf(building.getEnergiaklass().toUpperCase());
                energyTotals.computeIfPresent(energyClass, (key, value) -> value + 1);
            } catch (IllegalArgumentException e) {
                log.warn("Unknown or invalid energy class detected");
            }
        }

        return energyTotals;
    }

}
