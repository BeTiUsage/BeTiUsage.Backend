package home.betiusage.controllers;

import home.betiusage.dto.EconomicDetailsDTO;
import home.betiusage.services.EconomicDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/economicdetails")
public class EconomicDetailController {
    EconomicDetailService economicDetailService;

    public EconomicDetailController(EconomicDetailService economicDetailService) {
        this.economicDetailService = economicDetailService;
    }

    @GetMapping()
    public ResponseEntity<List<EconomicDetailsDTO>> getEconomicDetail() {
        try {
            List<EconomicDetailsDTO> details = economicDetailService.getEconomicDetails();
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
