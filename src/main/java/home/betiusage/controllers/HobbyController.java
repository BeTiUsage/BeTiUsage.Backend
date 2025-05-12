package home.betiusage.controllers;

import home.betiusage.dto.HobbyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import home.betiusage.services.HobbyService;

import java.util.List;

@RestController
@RequestMapping("/api/hobbies")
public class HobbyController {
    private final HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {this.hobbyService = hobbyService;}

    @GetMapping
    public ResponseEntity<List<HobbyDTO>> getHobbies() {
        return ResponseEntity.ok(hobbyService.findAll());
    }
}
