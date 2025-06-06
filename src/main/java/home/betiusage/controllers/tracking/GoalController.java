package home.betiusage.controllers.tracking;

import home.betiusage.dto.tracking.CreateGoalRequest;
import home.betiusage.dto.tracking.GoalDTO;
import home.betiusage.dto.tracking.UpdateGoalProgressRequest;
import home.betiusage.services.tracking.GoalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "*")
public class GoalController {

    private GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping("/custom")
    public ResponseEntity<GoalDTO> createCustomGoal(@RequestBody CreateGoalRequest request) {
        try{
            GoalDTO goal = goalService.createCustomGoal(request);
            return ResponseEntity.ok(goal);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/from-template")
    public ResponseEntity<GoalDTO> createGoalFromTemplate(@RequestParam Long trackingId,
                                                          @RequestParam Long templateId) {
        try {
            GoalDTO goal = goalService.createGoalFromTemplate(trackingId, templateId);
            return ResponseEntity.ok(goal);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tracking/{trackingId}")
    public ResponseEntity<List<GoalDTO>> getTrackingGoals(@PathVariable Long trackingId) {
        try{
            List<GoalDTO> goals = goalService.getTrackingGoals(trackingId);
            return ResponseEntity.ok(goals);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tracking/{trackingId}/active")
    public ResponseEntity<List<GoalDTO>> getActiveGoals(@PathVariable Long trackingId) {
        try{
            List<GoalDTO> goals = goalService.getActiveGoals(trackingId);
            return ResponseEntity.ok(goals);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{goalId}/progress")
    public ResponseEntity<GoalDTO> updateGoalProgress(@PathVariable Long goalId,
                                                      @RequestBody UpdateGoalProgressRequest request) {
        try {
            GoalDTO goal = goalService.updateGoalProgress(goalId, request);
            return ResponseEntity.ok(goal);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long goalId) {
        try {
            goalService.deleteGoal(goalId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
