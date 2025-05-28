package home.betiusage.controllers;

import home.betiusage.dto.GoalDTO;
import home.betiusage.services.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public ResponseEntity<List<GoalDTO>> getGoals() {
        return ResponseEntity.ok(goalService.findAll());
    }

    @PostMapping
    public ResponseEntity<GoalDTO> createGoal(@RequestBody GoalDTO goalDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(goalService.createGoal(goalDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoalDTO> updateGoal(@RequestBody GoalDTO goalDTO, @PathVariable Long id) {
        return ResponseEntity.ok(goalService.updateGoal(goalDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GoalDTO> deleteGoal(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.deleteGoal(id));
    }

    @DeleteMapping("/{id}/subgoals/{subgoalId}")
    public ResponseEntity<GoalDTO> deleteSubGoal(@PathVariable Long id, @PathVariable Long subgoalId) {
        return ResponseEntity.ok(goalService.deleteSubgoal(id, subgoalId));
    }

}
