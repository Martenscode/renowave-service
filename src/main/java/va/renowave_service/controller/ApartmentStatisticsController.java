package va.renowave_service.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import va.renowave_service.common.ApartmentStatisticsRequestDto;
import va.renowave_service.database.entity.ApartmentStatistics;
import va.renowave_service.service.ApartmentStatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor
public class ApartmentStatisticsController {

    private ApartmentStatisticsService apartmentStatisticsService;

    @GetMapping
    public ResponseEntity<List<ApartmentStatistics>> getAllStatistics() {
        return new ResponseEntity<>(apartmentStatisticsService.getAllStatistics(), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<ApartmentStatistics> updateStatistics(@RequestBody @Valid ApartmentStatisticsRequestDto dto) {
        return new ResponseEntity<>(apartmentStatisticsService.updateStatistics(dto), HttpStatus.OK);
    }

}
