package home.betiusage.services;

import home.betiusage.dto.GoalDTO;
import home.betiusage.dto.SubGoalDTO;
import home.betiusage.entities.Goal;
import home.betiusage.entities.SubGoal;
import home.betiusage.entities.Tracking;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.repositories.GoalRepository;
import home.betiusage.repositories.TrackingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {
    private final GoalRepository goalRepository;
    private final TrackingRepository trackingRepository;

    public GoalService(GoalRepository goalRepository, TrackingRepository trackingRepository) {
        this.goalRepository = goalRepository;
        this.trackingRepository = trackingRepository;
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
    public GoalDTO updateGoalCompletedStatus(GoalDTO goalDTO, Long id) {
        if (id == null) {
            throw new NotFoundException("ID should not be null");
        }

        Goal existingGoal = goalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Goal not found with id: " + id));

        // Check if trying to mark goal as completed
        if (goalDTO.getCompleted() != null && goalDTO.getCompleted()) {
            // Verify all subgoals are completed
            boolean allSubgoalsCompleted = existingGoal.getSubGoals().isEmpty() ||
                    existingGoal.getSubGoals().stream()
                            .allMatch(sg -> sg.getCompleted() != null && sg.getCompleted());

            if (!allSubgoalsCompleted) {
                throw new IllegalStateException("Cannot mark goal as completed when subgoals are incomplete");
            }

            // Only award XP if the goal wasn't previously completed
            boolean wasAlreadyCompleted = existingGoal.getCompleted() != null && existingGoal.getCompleted();
            if (!wasAlreadyCompleted) {
                awardXpForGoalCompletion(existingGoal);
            }
        }

        // Update the goal's completion status
        if (goalDTO.getCompleted() != null) {
            existingGoal.setCompleted(goalDTO.getCompleted());
        }

        // Save and return the updated goal
        return toDTO(goalRepository.save(existingGoal));
    }

    private void awardXpForGoalCompletion(Goal goal) {
        Tracking tracking = goal.getTracking();
        if (tracking == null) {
            return; // No tracking to update
        }

        // Initialize XP if it's null
        if (tracking.getXp() == null) {
            tracking.setXp(0);
        }

        // Award XP for goal completion
        tracking.setXp(tracking.getXp() + XpService.getGoalCompletionBonusXp());

        // Save the tracking with updated XP
        trackingRepository.save(tracking);

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
        goalDTO.setTrackingId(goal.getTracking() != null ? goal.getTracking().getId() : null);

        if (goal.getSubGoals() != null && !goal.getSubGoals().isEmpty()) {
            List<SubGoalDTO> subGoalDTOs = goal.getSubGoals().stream()
                    .map(this::toSubGoalDTO)  // Make sure this method exists
                    .collect(Collectors.toList());
            goalDTO.setSubGoals(subGoalDTOs);
        } else {
            goalDTO.setSubGoals(new ArrayList<>());
        }

        return goalDTO;
    }

    private SubGoalDTO toSubGoalDTO(SubGoal subGoal) {
        SubGoalDTO subGoalDTO = new SubGoalDTO();
        subGoalDTO.setId(subGoal.getId());
        subGoalDTO.setName(subGoal.getName());
        subGoalDTO.setCompleted(subGoal.getCompleted());
        return subGoalDTO;
    }


    public Goal ToEntity(GoalDTO goalDTO) {
        Goal goal = new Goal();
        goal.setId(goalDTO.getId());
        goal.setName(goalDTO.getName());
        goal.setCompleted(goalDTO.getCompleted());
        goal.setTracking(goalDTO.getTrackingId() != null ? new Tracking() : null);

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
