package home.betiusage.controllers;

import home.betiusage.dto.EconomicDetailsDTO;
import home.betiusage.services.EconomicDetailService;
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

    @GetMapping("hobbyid/{id}")
    public ResponseEntity<List<EconomicDetailsDTO>> getEconomicDetailByHobbyId(@PathVariable Long id) {
        List<EconomicDetailsDTO> details = economicDetailService.getEconomicDetailByHobbyId(id);
        return ResponseEntity.ok(details);
    }
}
