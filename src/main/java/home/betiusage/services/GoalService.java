package home.betiusage.services;

import home.betiusage.dto.GoalDTO;
import home.betiusage.dto.SubGoalDTO;
import home.betiusage.entities.Goal;
import home.betiusage.entities.SubGoal;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.repositories.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Goal savedGoal = goalRepository.save(goal);
        return toDTO(savedGoal);
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

        Goal updatedGoal = goalRepository.save(existingGoal);
        return toDTO(updatedGoal);
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

    public Goal toEntity(GoalDTO goalDTO) {
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
