package home.betiusage.controllers;

import home.betiusage.entities.Profile;
import home.betiusage.services.ProfileService;
import home.betiusage.utils.CurrentUserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import home.betiusage.dto.ProfileDTO;

import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final CurrentUserUtil currentUserUtil;

    public ProfileController(ProfileService profileService, CurrentUserUtil currentUserUtil) {
        this.profileService = profileService;
        this.currentUserUtil = currentUserUtil;
    }

    @GetMapping()
    public ResponseEntity<Optional<ProfileDTO>> getProfile() {
        try {
            Profile profile = currentUserUtil.getCurrentProfile();
            return ResponseEntity.ok(profileService.findProfile(profile.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<ProfileDTO> deleteCurrentProfile() {
        try {
            Profile profile = currentUserUtil.getCurrentProfile();
            return ResponseEntity.ok(profileService.deleteCurrentProfile(profile.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}