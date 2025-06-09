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

import static home.betiusage.utils.ValidationUtils.existsById;
import static home.betiusage.utils.ValidationUtils.validateId;

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

        if (goalDTO.getTrackingId() != null) {
            Tracking tracking = trackingRepository.findById(goalDTO.getTrackingId())
                    .orElseThrow(() -> new NotFoundException("Tracking not found with id: " + goalDTO.getTrackingId()));
            goal.setTracking(tracking);
        }

        Goal savedGoal = goalRepository.save(goal);

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

        if (goalDTO.getSubGoals() != null) {
            List<SubGoal> updatedSubGoals = new ArrayList<>();

            for (SubGoalDTO subGoalDTO : goalDTO.getSubGoals()) {
                SubGoal subGoal;
                boolean wasCompletedBefore = false;

                if (subGoalDTO.getId() != null) {
                    Optional<SubGoal> existingSubGoal = existingGoal.getSubGoals().stream()
                            .filter(sg -> sg.getId().equals(subGoalDTO.getId()))
                            .findFirst();

                    subGoal = existingSubGoal.orElseGet(() -> subGoalRepository.findById(subGoalDTO.getId())
                            .orElseGet(SubGoal::new));
                    wasCompletedBefore = subGoal.getCompleted() != null && subGoal.getCompleted();
                } else {
                    subGoal = new SubGoal();
                }

                if (subGoalDTO.getName() != null) {
                    subGoal.setName(subGoalDTO.getName());
                }

                if (subGoalDTO.getCompleted() == null) {
                    subGoal.setCompleted(false);
                } else {
                    subGoal.setCompleted(subGoalDTO.getCompleted());
                }

                if (subGoalDTO.getCompleted() != null) {
                    boolean isCompletedNow = subGoalDTO.getCompleted();

                    if (isCompletedNow && !wasCompletedBefore) {
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
            boolean allSubgoalsCompleted = existingGoal.getSubGoals().isEmpty() ||
                    existingGoal.getSubGoals().stream()
                            .allMatch(sg -> sg.getCompleted() != null && sg.getCompleted());

            if (!allSubgoalsCompleted) {
                throw new IllegalStateException("Cannot mark goal as completed when subgoals are incomplete");
            }

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

    public void awardXpForGoalCompletion(Goal goal) {
        Tracking tracking = goal.getTracking();
        if (tracking == null) {
            return;
        }

        if (tracking.getXp() == null) {
            tracking.setXp(0);
        }

        tracking.setXp(tracking.getXp() + XpService.getGoalCompletionBonusXp());

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

    public boolean isGoalTracked(Long goalId) {
        validateId(goalId, "goal");
        existsById(goalRepository, goalId, "Goal");

        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new NotFoundException("Goal not found with id: " + goalId));

        return goal.getTracking() != null;
    }

    public boolean areAnyGoalsTracked(List<Long> goalIds) {
        if (goalIds == null || goalIds.isEmpty()) {
            return false;
        }

        for (Long goalId : goalIds) {
            if (isGoalTracked(goalId)) {
                return true;
            }
        }

        return false;
    }

    public GoalDTO toDTO(Goal goal) {
        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(goal.getId());
        goalDTO.setName(goal.getName());
        goalDTO.setCompleted(goal.getCompleted());
        goalDTO.setTrackingId(goal.getTracking() != null ? goal.getTracking().getId() : null);
        goalDTO.setGoalNumber(goal.getGoalNumber());
        goalDTO.setHobbyName(goal.getHobbyName());
        goalDTO.setIsTemplate(goal.getIsTemplate());

        if (goal.getSubGoals() != null && !goal.getSubGoals().isEmpty()) {
            List<SubGoalDTO> subGoalDTOs = goal.getSubGoals().stream()
                    .map(this::toSubGoalDTO)
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
