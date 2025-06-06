package home.betiusage.controllers.tracking;

import home.betiusage.dto.tracking.ActivityLogDTO;
import home.betiusage.services.tracking.ActivityLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private ActivityLogService activityLogService;

    public ActivityController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping("/tracking/{trackingId}")
    public ResponseEntity<List<ActivityLogDTO>> getTrackingActivities(@PathVariable Long trackingId) {
        try {
            List<ActivityLogDTO> activities = activityLogService.getTrackingActivities(trackingId);
            return ResponseEntity.ok(activities);
        } catch ( Exception e ) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/tracking/{trackingId}/recent")
    public ResponseEntity<List<ActivityLogDTO>> getRecentActivities(@PathVariable Long trackingId,
                                                                    @RequestParam(defaultValue = "7") Integer days) {
        try {
            List<ActivityLogDTO> activities = activityLogService.getRecentActivities(trackingId, days);
            return ResponseEntity.ok(activities);
        } catch ( Exception e ) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/custom")
    public ResponseEntity<Void> logCustomActivity(@RequestParam Long trackingId,
                                                  @RequestParam String description,
                                                  @RequestParam Integer xp,
                                                  @RequestParam(required = false) String notes) {
        try {
            activityLogService.logCustomActivity(trackingId, description, xp, notes);
            return ResponseEntity.ok().build();
        } catch ( Exception e ) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}