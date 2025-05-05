package home.betiusage.controllers;

import home.betiusage.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import home.betiusage.dto.ProfileDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>>getProfiles() {
         return ResponseEntity.ok(profileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO>findProfileById(@PathVariable Long id) {
        return ResponseEntity.of(profileService.findProfile(id));
    }

    @PostMapping("/createProfile")
    public ResponseEntity<ProfileDTO>createProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO createdProfile = profileService.createProfile(profileDTO);
        URI location = URI.create("/api/profiles/" + createdProfile.getId());
        return ResponseEntity.created(location).body(createdProfile);
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<ProfileDTO>updateProfile(@RequestBody ProfileDTO profileDTO, @PathVariable Long id) {
        return ResponseEntity.ok(profileService.updateProfile(profileDTO, id));
    }

    @DeleteMapping("/deleteProfile/{id}")
    public ResponseEntity<ProfileDTO> deleteProfile(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.deleteProfile(id));
    }
}
