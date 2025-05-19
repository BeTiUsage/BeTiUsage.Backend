package home.betiusage.controllers;

import home.betiusage.dto.TrackingDTO;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.services.TrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/trackings")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<TrackingDTO>> findAllByProfileId(@PathVariable Long profileId) {
        List<TrackingDTO> trackings = trackingService.findAllByProfileId(profileId);
        return ResponseEntity.ok(trackings);
    }

    @GetMapping("/profile/{profileId}/tracking/{trackingId}")
    public ResponseEntity<TrackingDTO> findByIdAndProfileId(@PathVariable Long profileId, @PathVariable Long trackingId) {
        TrackingDTO tracking = trackingService.findByIdAndProfileId(profileId, trackingId)
                .orElseThrow(() -> new NotFoundException("Tracking not found"));
        return ResponseEntity.ok(tracking);
    }

    @PostMapping
    public ResponseEntity<TrackingDTO> createTracking(@RequestBody TrackingDTO trackingDTO) {
        TrackingDTO createdTracking = trackingService.createTracking(trackingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTracking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackingDTO> updateTracking(@RequestBody TrackingDTO trackingDTO, @PathVariable Long id) {
        TrackingDTO updatedTracking = trackingService.updateTracking(trackingDTO, id);
        return ResponseEntity.ok(updatedTracking);
    }
}
