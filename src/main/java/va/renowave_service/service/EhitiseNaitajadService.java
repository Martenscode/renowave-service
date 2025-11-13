package va.renowave_service.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import va.renowave_service.common.ehr.EhitiseNaitajadRequestDto;
import va.renowave_service.common.ehr.EhitiseNaitajadResponseDto;
import va.renowave_service.common.exceptions.ApartmentCountException;
import va.renowave_service.config.EhrConfig;
import va.renowave_service.database.entity.MonthlyBuildingCount;
import va.renowave_service.database.repository.MonthlyBuildingCountRepository;
import va.renowave_service.integration.ehr.EhrApiClient;
import va.renowave_service.utils.KovUtil;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EhitiseNaitajadService {

    private final EhrApiClient ehrApiClient;
    private final MonthlyBuildingCountRepository repository;
    private final KovUtil kovUtil = new KovUtil();

    @Transactional
    public void fetchAndSaveApartmentBuildings(List<String> kovs) {
        EhitiseNaitajadResponseDto res = ehrApiClient.fetchEhitiseNaitajad(buildEhitiseNaitajadRequest(kovs));
        int totalApts = countApartmentBuildings(res);

        MonthlyBuildingCount count = createCount(totalApts, kovs);
        try {
            repository.save(count);
        } catch (Exception e) {
            log.error("Failed to save apartment count record for {}", kovs, e);
        }
    }

    private MonthlyBuildingCount createCount(Integer totalApts, List<String> kovs) {
        LocalDate now = LocalDate.now();

        MonthlyBuildingCount count = new MonthlyBuildingCount();
        count.setYear(now.getYear());
        count.setMonth(YearMonth.from(now).atDay(1));
        count.setMunicipality(kovUtil.getKovFromCodes(kovs).getName());
        count.setAptCount(totalApts);
        return count;
    }

    private EhitiseNaitajadRequestDto buildEhitiseNaitajadRequest(List<String> kovs) {
        EhitiseNaitajadRequestDto req = new EhitiseNaitajadRequestDto();
        req.setAlgusAasta(0);
        req.setLoppAasta(9999);
        req.setEhitiseSeisund(List.of("Olemas"));
        req.setKaos(EhrConfig.DEFAULT_KAOS);
        req.setKov(kovs);
        req.setOtsiAastata(true);
        req.setOtsiKovita(false);
        req.setTaseSisu(List.of("all"));
        return req;
    }

    private int countApartmentBuildings(EhitiseNaitajadResponseDto response) {
        if (response == null || response.getData() == null) {
            throw new ApartmentCountException("Invalid response. Unable to calculate apartment building count.");
        }
        return response.getData().stream()
                .mapToInt(EhitiseNaitajadResponseDto.EhitiseNaitajadData::getEhitisi)
                .sum();
    }

}
