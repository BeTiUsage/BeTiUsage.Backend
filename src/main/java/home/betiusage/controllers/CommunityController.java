package home.betiusage.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import home.betiusage.dto.CommunityDTO;
import home.betiusage.services.CommunityService;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> findAll () {
        return ResponseEntity.ok(communityService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<CommunityDTO> findById(@PathVariable Long id) {
        return ResponseEntity.of(communityService.findById(id));
    }

    @GetMapping("/hobby/{id}")
    public ResponseEntity<List<CommunityDTO>> findByHobbyId(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.findByHobbyId(id));
    }
}
