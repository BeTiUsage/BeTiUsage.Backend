package home.betiusage.controllers.tracking;

import home.betiusage.dto.tracking.TrackingDTO;
import home.betiusage.entities.Profile;
import home.betiusage.services.tracking.TrackingService;
import home.betiusage.utils.CurrentUserUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final TrackingService trackingService;
    private final CurrentUserUtil currentUserUtil;

    public TrackingController(TrackingService trackingService, CurrentUserUtil currentUserUtil) {
        this.trackingService = trackingService;
        this.currentUserUtil = currentUserUtil;
    }

    @PostMapping("/start")
    public ResponseEntity<TrackingDTO> startTracking(@RequestParam Long hobbyId) {
        try {
            Profile profile = currentUserUtil.getCurrentProfile();
            TrackingDTO tracking = trackingService.startHobbyTracking(profile.getId(), hobbyId);
            return ResponseEntity.ok(tracking);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/")
    public ResponseEntity<List<TrackingDTO>> getUserTrackings() {
        try {
            Profile profile = currentUserUtil.getCurrentProfile();
            List<TrackingDTO> trackings = trackingService.getUserTrackings(profile.getId());
            return ResponseEntity.ok(trackings);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackingDTO> getTracking(@PathVariable Long trackingId) {
        try {
            TrackingDTO tracking = trackingService.getTracking(trackingId);
            return ResponseEntity.ok(tracking);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{trackingId}/xp")
    public ResponseEntity<TrackingDTO> addXp(@PathVariable Long trackingId,
                                             @RequestParam Integer xp,
                                             @RequestParam(required = false) String reason) {
        try {
            TrackingDTO tracking = trackingService.addXp(trackingId, xp, reason);
            return ResponseEntity.ok(tracking);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{trackingId}/streak")
    public ResponseEntity<Void> updateStreak(@PathVariable Long trackingId) {
        try {
            trackingService.updateStreak(trackingId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}