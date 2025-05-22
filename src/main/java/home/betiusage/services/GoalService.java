package home.betiusage.services;

import home.betiusage.dto.GoalDTO;
import home.betiusage.dto.SubGoalDTO;
import home.betiusage.entities.Goal;
import home.betiusage.entities.SubGoal;
import home.betiusage.entities.Tracking;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.repositories.GoalRepository;
import home.betiusage.repositories.SubGoalRepository;
import home.betiusage.repositories.TrackingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoalService {
    private final GoalRepository goalRepository;
    private final SubGoalService subGoalService;
    private final SubGoalRepository subGoalRepository;
    private final TrackingRepository trackingRepository;

    public GoalService(GoalRepository goalRepository, SubGoalService subGoalService, SubGoalRepository subGoalRepository, TrackingRepository trackingRepository) {
        this.goalRepository = goalRepository;
        this.subGoalService = subGoalService;
        this.subGoalRepository = subGoalRepository;
        this.trackingRepository = trackingRepository;
    }

    public List<GoalDTO> findAll() {
        return goalRepository.findAll().stream().map(this::toDTO).toList();
    }

    public GoalDTO createGoal(GoalDTO goalDTO) {
        Goal goal = new Goal();
        goal.setName(goalDTO.getName());
        goal.setCompleted(goalDTO.getCompleted());
        goal.setGoalNumber(goalDTO.getGoalNumber());

        // Handle tracking relationship properly
        if (goalDTO.getTrackingId() != null) {
            Tracking tracking = trackingRepository.findById(goalDTO.getTrackingId())
                    .orElseThrow(() -> new NotFoundException("Tracking not found with id: " + goalDTO.getTrackingId()));
            goal.setTracking(tracking);
        }

        // Save the goal first to get an ID
        Goal savedGoal = goalRepository.save(goal);

        // Handle SubGoals that might be part of the request
        if (goalDTO.getSubGoals() != null && !goalDTO.getSubGoals().isEmpty()) {
            List<SubGoal> subGoalsList = new ArrayList<>();

            for (SubGoalDTO subGoalDTO : goalDTO.getSubGoals()) {
                SubGoal subGoal;

                if (subGoalDTO.getId() != null) {
                    subGoal = subGoalRepository.findById(subGoalDTO.getId())
                            .orElseThrow(() -> new NotFoundException("SubGoal not found with id: " + subGoalDTO.getId()));
                } else {
                    subGoal = new SubGoal();
                }

                // Set properties from DTO
                subGoal.setName(subGoalDTO.getName());
                subGoal.setCompleted(subGoalDTO.getCompleted() != null ? subGoalDTO.getCompleted() : false);
                subGoal.setGoal(savedGoal);

                subGoalsList.add(subGoal);
            }

            subGoalsList = subGoalRepository.saveAll(subGoalsList);
            savedGoal.setSubGoals(subGoalsList);
        }

        return toDTO(savedGoal);
    }

    @Transactional
    public GoalDTO updateGoal(GoalDTO goalDTO, Long id) {
        if (id == null) {
            throw new NotFoundException("ID should not be null");
        }

        Goal existingGoal = goalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Goal not found with id: " + id));

        if (goalDTO.getName() != null) {
            existingGoal.setName(goalDTO.getName());
        }

        if (goalDTO.getGoalNumber() != null) {
            existingGoal.setGoalNumber(goalDTO.getGoalNumber());
        }

        if (goalDTO.getTrackingId() != null) {
            Tracking tracking = trackingRepository.findById(goalDTO.getTrackingId())
                    .orElseThrow(() -> new NotFoundException("Tracking not found with id: " + goalDTO.getTrackingId()));
            existingGoal.setTracking(tracking);
        }

        // Handle subgoals if provided
        if (goalDTO.getSubGoals() != null) {
            List<SubGoal> updatedSubGoals = new ArrayList<>();

            for (SubGoalDTO subGoalDTO : goalDTO.getSubGoals()) {
                SubGoal subGoal;
                boolean wasCompletedBefore = false;

                if (subGoalDTO.getId() != null) {
                    Optional<SubGoal> existingSubGoal = existingGoal.getSubGoals().stream()
                            .filter(sg -> sg.getId().equals(subGoalDTO.getId()))
                            .findFirst();

                    if (existingSubGoal.isPresent()) {
                        subGoal = existingSubGoal.get();
                        wasCompletedBefore = subGoal.getCompleted() != null && subGoal.getCompleted();
                    } else {
                        subGoal = subGoalRepository.findById(subGoalDTO.getId())
                                .orElseGet(SubGoal::new);
                        wasCompletedBefore = subGoal.getCompleted() != null && subGoal.getCompleted();
                    }
                } else {
                    subGoal = new SubGoal();
                    wasCompletedBefore = false;
                }

                if (subGoalDTO.getName() != null) {
                    subGoal.setName(subGoalDTO.getName());
                }

                if (subGoalDTO.getCompleted() == null) {
                    subGoal.setCompleted(false);
                } else {
                    subGoal.setCompleted(subGoalDTO.getCompleted());
                }

                // Update completion status if provided and award XP only when status changes from false to true
                if (subGoalDTO.getCompleted() != null) {
                    boolean isCompletedNow = subGoalDTO.getCompleted();

                    // Check if completion status is changing from incomplete to complete
                    if (isCompletedNow && !wasCompletedBefore) {
                        // Set the goal relationship first so XP calculation works properly
                        subGoal.setGoal(existingGoal);

                        subGoalService.awardXpForSubgoalCompletion(subGoal);
                    }

                    subGoal.setCompleted(isCompletedNow);
                }

                subGoal.setGoal(existingGoal);
                updatedSubGoals.add(subGoal);
            }

            existingGoal.getSubGoals().clear();
            existingGoal.getSubGoals().addAll(updatedSubGoals);
        }

        boolean goalCompletionRequested = goalDTO.getCompleted() != null && goalDTO.getCompleted();
        if (goalCompletionRequested) {
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

        if (goalDTO.getCompleted() != null) {
            existingGoal.setCompleted(goalDTO.getCompleted());
        }

        Goal savedGoal = goalRepository.save(existingGoal);
        return toDTO(savedGoal);
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

        GoalDTO goalDTO = toDTO(existingGoal);

        if (existingGoal.getTracking() != null) {
            Tracking tracking = existingGoal.getTracking();
            tracking.getGoals().remove(existingGoal);
            existingGoal.setTracking(null);
        }

        List<Long> subgoalIds = existingGoal.getSubGoals().stream()
                .map(SubGoal::getId)
                .toList();

        for (SubGoal subGoal : new ArrayList<>(existingGoal.getSubGoals())) {
            subGoal.setGoal(null);
        }
        existingGoal.getSubGoals().clear();

        goalRepository.saveAndFlush(existingGoal);

        for (Long subgoalId : subgoalIds) {
            subGoalRepository.deleteById(subgoalId);
        }

        goalRepository.delete(existingGoal);
        goalRepository.flush();

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

        existingGoal.getSubGoals().remove(existingSubGoal);

        existingSubGoal.setGoal(null);

        goalRepository.saveAndFlush(existingGoal);

        subGoalRepository.deleteById(subGoalId);

        return toDTO(existingGoal);
    }

    public GoalDTO toDTO(Goal goal) {
        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(goal.getId());
        goalDTO.setName(goal.getName());
        goalDTO.setCompleted(goal.getCompleted());
        goalDTO.setTrackingId(goal.getTracking() != null ? goal.getTracking().getId() : null);
        goalDTO.setGoalNumber(goal.getGoalNumber());

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
}
