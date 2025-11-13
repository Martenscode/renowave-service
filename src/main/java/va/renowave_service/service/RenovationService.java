package va.renowave_service.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import va.renowave_service.common.RenovationRequestDto;
import va.renowave_service.database.entity.Renovation;
import va.renowave_service.database.repository.RenovationRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RenovationService {

    private RenovationRepository renovationRepository;

    public List<Renovation> getAllRenovations() {
        return renovationRepository.findAll(Sort.by(Sort.Direction.DESC, "year"));
    }

    public Renovation saveRenovation(RenovationRequestDto renovationRequest) {
        Renovation newRenovation = mapRenovation(renovationRequest);
        return renovationRepository.save(newRenovation);
    }

    public Renovation updateRenovation(RenovationRequestDto renovationRequest) {
        Renovation existingRenovation = renovationRepository.findById(renovationRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Renovation not found with id " + renovationRequest.getId()));

        existingRenovation.setYear(renovationRequest.getYear());
        existingRenovation.setAntslaVald(renovationRequest.getAntslaVald());
        existingRenovation.setRougeVald(renovationRequest.getRougeVald());
        existingRenovation.setSetomaaVald(renovationRequest.getSetomaaVald());
        existingRenovation.setVoruLinn(renovationRequest.getVoruLinn());
        existingRenovation.setVoruVald(renovationRequest.getVoruVald());
        existingRenovation.setCountyTotal(renovationRequest.getCountyTotal());

        return renovationRepository.save(existingRenovation);
    }

    public void deleteRenovation(Integer id) {
        if (!renovationRepository.existsById(id)) {
            throw new IllegalArgumentException("Renovation not found with id " + id);
        }
        renovationRepository.deleteById(id);
    }

    private Renovation mapRenovation(RenovationRequestDto req) {
        Renovation r = new Renovation();
        r.setYear(req.getYear());
        r.setAntslaVald(req.getAntslaVald());
        r.setRougeVald(req.getRougeVald());
        r.setSetomaaVald(req.getSetomaaVald());
        r.setVoruLinn(req.getVoruLinn());
        r.setVoruVald(req.getVoruVald());
        r.setCountyTotal(req.getCountyTotal());
        return r;
    }
}
