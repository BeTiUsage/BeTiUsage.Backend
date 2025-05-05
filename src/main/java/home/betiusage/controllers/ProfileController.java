package home.betiusage.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import home.betiusage.dto.ProfileDto;
import home.betiusage.services.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>>findAll() {
         return ResponseEntity.ok(profileService.findAll());
    }
}
