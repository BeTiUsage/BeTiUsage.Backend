package home.betiusage.controllers;

import org.springframework.http.HttpStatus;
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
        try {
            return ResponseEntity.ok(communityService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
