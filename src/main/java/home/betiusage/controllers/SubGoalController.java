package home.betiusage.controllers;

import home.betiusage.dto.SubGoalDTO;
import home.betiusage.services.SubGoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("/api/public/subgoals")
public class SubGoalController {

    private final SubGoalService subGoalService;

    public SubGoalController(SubGoalService subGoalService) {
        this.subGoalService = subGoalService;
    }

    @GetMapping
    public ResponseEntity<List<SubGoalDTO>> getSubGoals() {
        List<SubGoalDTO> subGoals = subGoalService.findAll();
        return ResponseEntity.ok(subGoals);
    }

    @PostMapping
    public ResponseEntity<SubGoalDTO> createSubGoal(SubGoalDTO subGoalDTO) {
        SubGoalDTO createdSubGoal = subGoalService.createSubGoal(subGoalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubGoal);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubGoalDTO> updateSubGoal(@RequestBody SubGoalDTO subGoalDTO, @RequestParam Long id) {
        SubGoalDTO updatedSubGoal = subGoalService.updateSubGoal(subGoalDTO, id);
        return ResponseEntity.ok(updatedSubGoal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubGoalDTO> deleteSubGoal(@PathVariable Long id) {
        subGoalService.deleteSubGoal(id);
        return ResponseEntity.ok().build();
    }
}
