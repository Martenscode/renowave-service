package va.renowave_service.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import va.renowave_service.common.ApartmentStatisticsRequestDto;
import va.renowave_service.database.entity.ApartmentStatistics;
import va.renowave_service.database.repository.ApartmentStatisticsRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ApartmentStatisticsService {

    private final ApartmentStatisticsRepository apartmentStatisticsRepository;

    public List<ApartmentStatistics> getAllStatistics() {
        return apartmentStatisticsRepository.findAll(Sort.by(Sort.Direction.DESC, "kovName"));
    }

    public ApartmentStatistics updateStatistics(ApartmentStatisticsRequestDto requestDto) {
        ApartmentStatistics existingStatistics = apartmentStatisticsRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Statistic not found with id " + requestDto.getId()));

        existingStatistics.setKaheKolme(requestDto.getKaheKolme());
        existingStatistics.setKuni12(requestDto.getKuni12());
        existingStatistics.setRohkemKui12(requestDto.getRohkemKui12());
        existingStatistics.setEriliseltSuur(requestDto.getEriliseltSuur());

        return apartmentStatisticsRepository.save(existingStatistics);
    }

}
