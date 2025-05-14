package home.betiusage.services;

import home.betiusage.dto.GoalDTO;
import home.betiusage.dto.SubGoalDTO;
import home.betiusage.entities.Goal;
import home.betiusage.entities.SubGoal;
import home.betiusage.entities.Tracking;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.repositories.GoalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {
    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<GoalDTO> findAll() {
        return goalRepository.findAll().stream().map(this::toDTO).toList();
    }

    public GoalDTO createGoal(GoalDTO goalDTO) {
        Goal goal = new Goal();
        goal.setName(goalDTO.getName());
        goal.setCompleted(goalDTO.getCompleted());

        if (goalDTO.getSubGoals() != null) {
            goal.setSubGoals(
                    goalDTO.getSubGoals().stream()
                            .map(subGoalDTO -> {
                                SubGoal subGoal = new SubGoal();
                                subGoal.setName(subGoalDTO.getName());
                                subGoal.setCompleted(subGoalDTO.getCompleted());
                                return subGoal;
                            })
                            .toList()
            );
        }


        return toDTO(goalRepository.save(ToEntity(goalDTO)));
    }

    public GoalDTO updateGoal(GoalDTO goalDTO, Long id) {
        Goal existingGoal = goalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Goal not found with id: " + id));

        if (goalDTO.getName() != null) {
            existingGoal.setName(goalDTO.getName());
        }
        if (goalDTO.getCompleted() != null) {
            existingGoal.setCompleted(goalDTO.getCompleted());
        }

        if (goalDTO.getSubGoals() != null) {
            existingGoal.setSubGoals(
                    goalDTO.getSubGoals().stream()
                            .map(subGoalDTO -> {
                                SubGoal subGoal = new SubGoal();
                                subGoal.setName(subGoalDTO.getName());
                                subGoal.setCompleted(subGoalDTO.getCompleted());
                                return subGoal;
                            })
                            .toList()
            );
        }

        return toDTO(goalRepository.save(ToEntity(goalDTO)));
    }

    @Transactional
    public GoalDTO deleteGoal(Long id) {
        if (id == null) {
            throw new NotFoundException("ID should not be null");
        }

        Goal existingGoal = goalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Goal not found with id: " + id));

        // Create DTO before deletion
        GoalDTO goalDTO = toDTO(existingGoal);

        // Handle the Tracking relationship
        if (existingGoal.getTracking() != null) {
            Tracking tracking = existingGoal.getTracking();
            tracking.getGoals().remove(existingGoal);
            existingGoal.setTracking(null);
        }

        // Manually break the bidirectional relationship with subgoals
        for (SubGoal subGoal : new ArrayList<>(existingGoal.getSubGoals())) {
            subGoal.setGoal(null);
        }
        existingGoal.getSubGoals().clear();

        // Save to update the relationships
        goalRepository.saveAndFlush(existingGoal);

        // Now delete the goal
        goalRepository.delete(existingGoal);
        goalRepository.flush();

        return goalDTO;
    }

    @Transactional
    public GoalDTO deleteSubgoals(Long id) {
        if (id == null) {
            throw new NotFoundException("ID should not be null");
        }

        Goal existingGoal = goalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Goal not found with id: " + id));

        GoalDTO goalDTO = toDTO(existingGoal);

        List<SubGoal> subGoals = new ArrayList<>(existingGoal.getSubGoals());
        existingGoal.getSubGoals().clear();

        for (SubGoal subGoal : subGoals) {
            subGoal.setGoal(null);
        }

        goalRepository.saveAndFlush(existingGoal);

        return goalDTO;
    }

    @Transactional
    public GoalDTO deleteSubgoal(Long id, Long subGoalId) {
        if (id == null || subGoalId == null) {
            throw new NotFoundException("ID should not be null");
        }

        Goal existingGoal = goalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Goal not found with id: " + id));

        SubGoal existingSubGoal = existingGoal.getSubGoals().stream()
                .filter(subGoal -> subGoal.getId().equals(subGoalId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Subgoal not found with id: " + subGoalId));

        existingSubGoal.setGoal(null);
        existingGoal.getSubGoals().remove(existingSubGoal);

        goalRepository.saveAndFlush(existingGoal);

        return toDTO(existingGoal);
    }

    public GoalDTO toDTO(Goal goal) {
        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(goal.getId());
        goalDTO.setName(goal.getName());
        goalDTO.setCompleted(goal.getCompleted());

        if (goal.getSubGoals() != null) {
            goalDTO.setSubGoals(
                    goal.getSubGoals().stream()
                            .map(subGoal -> {
                                SubGoalDTO subGoalDTO = new SubGoalDTO();
                                subGoalDTO.setId(subGoal.getId());
                                subGoalDTO.setName(subGoal.getName());
                                subGoalDTO.setCompleted(subGoal.getCompleted());
                                return subGoalDTO;
                            })
                            .toList()
            );
        }
        return goalDTO;
    }

    public Goal ToEntity(GoalDTO goalDTO) {
        Goal goal = new Goal();
        goal.setId(goalDTO.getId());
        goal.setName(goalDTO.getName());
        goal.setCompleted(goalDTO.getCompleted());

        if (goalDTO.getSubGoals() != null) {
            goal.setSubGoals(
                    goalDTO.getSubGoals().stream()
                            .map(subGoalDTO -> {
                                SubGoal subGoal = new SubGoal();
                                subGoal.setId(subGoalDTO.getId());
                                subGoal.setName(subGoalDTO.getName());
                                subGoal.setCompleted(subGoalDTO.getCompleted());
                                return subGoal;
                            })
                            .toList()
            );
        }
        return goal;
    }
}
