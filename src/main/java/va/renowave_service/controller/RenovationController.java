package va.renowave_service.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import va.renowave_service.common.RenovationRequestDto;
import va.renowave_service.database.entity.Renovation;
import va.renowave_service.service.RenovationService;

import java.util.List;

@RestController
@RequestMapping("/api/renovation")
@AllArgsConstructor
public class RenovationController {

    private RenovationService renovationService;

    @GetMapping
    public ResponseEntity<List<Renovation>> getAllRenovations() {
        return new ResponseEntity<>(renovationService.getAllRenovations(), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Renovation> saveRenovation(@RequestBody @Valid RenovationRequestDto renovationRequest) {
        return new ResponseEntity<>(renovationService.saveRenovation(renovationRequest), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<Renovation> updateRenovation(@RequestBody @Valid RenovationRequestDto renovationRequest) {
        return new ResponseEntity<>(renovationService.updateRenovation(renovationRequest), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRenovation(@PathVariable Integer id) {
        renovationService.deleteRenovation(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
