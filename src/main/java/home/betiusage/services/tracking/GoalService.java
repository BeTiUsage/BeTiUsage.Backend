package home.betiusage.services.tracking;

import home.betiusage.dto.tracking.*;
import home.betiusage.entities.tracking.*;
import home.betiusage.enums.tracking.EGoalStatus;
import home.betiusage.repositories.tracking.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GoalService {

    private GoalRepository goalRepository;
    private SubGoalRepository subGoalRepository;
    private GoalTemplateRepository goalTemplateRepository;
    private SubGoalTemplateRepository subGoalTemplateRepository;
    private TrackingRepository trackingRepository;
    private ActivityLogService activityLogService;

    public GoalService(GoalRepository goalRepository, SubGoalRepository subGoalRepository, GoalTemplateRepository goalTemplateRepository, SubGoalTemplateRepository subGoalTemplateRepository, TrackingRepository trackingRepository, ActivityLogService activityLogService) {
        this.goalRepository = goalRepository;
        this.subGoalRepository = subGoalRepository;
        this.goalTemplateRepository = goalTemplateRepository;
        this.subGoalTemplateRepository = subGoalTemplateRepository;
        this.trackingRepository = trackingRepository;
        this.activityLogService = activityLogService;
    }

    public GoalDTO createCustomGoal(CreateGoalRequest request) {
        Goal goal = new Goal();
        goal.setTracking(new Tracking(request.getTrackingId()));
        goal.setTitle(request.getTitle());
        goal.setDescription(request.getDescription());
        goal.setTargetValue(request.getTargetValue());
        goal.setXpReward(request.getXpReward());
        goal.setType(request.getType());
        goal.setPriority(request.getPriority());
        goal.setDueDate(request.getDueDate());
        goal.setCreatedDate(LocalDateTime.now());
        goal.setIsCustom(true);
        goal.setStatus(EGoalStatus.ACTIVE);

        goal = goalRepository.save(goal);

        if (request.getSubGoals() != null) {
            for (CreateSubGoalRequest subGoalRequest : request.getSubGoals()) {
                createSubGoal(goal.getId(), subGoalRequest);
            }
        }

        return convertToDTO(goal);
    }

    public GoalDTO createGoalFromTemplate(Long trackingId, Long templateId) {
        GoalTemplate template = goalTemplateRepository.findById(templateId)
                .orElseThrow(() -> new EntityNotFoundException("Goal template not found"));

        Goal goal = new Goal();
        goal.setTracking(new Tracking(trackingId));
        goal.setGoalTemplate(template);
        goal.setTitle(template.getTitle());
        goal.setDescription(template.getDescription());
        goal.setTargetValue(template.getDefaultTargetValue());
        goal.setXpReward(template.getDefaultXpReward());
        goal.setType(template.getType());
        goal.setPriority(template.getPriority());
        goal.setCreatedDate(LocalDateTime.now());
        goal.setIsCustom(false);
        goal.setStatus(EGoalStatus.ACTIVE);

        goal = goalRepository.save(goal);

        List<SubGoalTemplate> subTemplates = subGoalTemplateRepository
                .findByGoalTemplateIdOrderByOrderIndex(templateId);

        for (SubGoalTemplate subTemplate : subTemplates) {
            createSubGoalFromTemplate(goal.getId(), subTemplate);
        }

        return convertToDTO(goal);
    }

    public GoalDTO updateGoalProgress(Long goalId, UpdateGoalProgressRequest request) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found"));

        goal.setCurrentValue(goal.getCurrentValue() + request.getProgressValue());

        if (goal.isCompleted() && goal.getStatus() != EGoalStatus.COMPLETED) {
            goal.setStatus(EGoalStatus.COMPLETED);
            goal.setCompletedDate(LocalDateTime.now());

            addXpToTracking(goal.getTracking().getId(), goal.getXpReward(),
                    "Completed goal: " + goal.getTitle());

            activityLogService.logGoalCompleted(goal, request.getNotes());
        }

        goal = goalRepository.save(goal);
        return convertToDTO(goal);
    }

    private void addXpToTracking(Long trackingId, Integer xp, String reason) {
        Tracking tracking = trackingRepository.findById(trackingId)
                .orElseThrow(() -> new EntityNotFoundException("Tracking not found"));

        Integer oldLevel = tracking.getCalculatedLevel();
        tracking.setTotalXp(tracking.getTotalXp() + xp);
        Integer newLevel = tracking.getCalculatedLevel();

        if (newLevel > oldLevel) {
            tracking.setCurrentLevel(newLevel);
            activityLogService.logLevelUp(tracking, newLevel);
        }

        trackingRepository.save(tracking);
    }

    public List<GoalDTO> getTrackingGoals(Long trackingId) {
        List<Goal> goals = goalRepository.findByTrackingId(trackingId);
        return goals.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<GoalDTO> getActiveGoals(Long trackingId) {
        List<Goal> goals = goalRepository.findByTrackingIdAndStatus(trackingId, EGoalStatus.ACTIVE);
        return goals.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found"));

        if (!goal.getIsCustom()) {
            throw new IllegalStateException("Cannot delete template-based goals");
        }

        goalRepository.delete(goal);
    }

    private SubGoal createSubGoal(Long goalId, CreateSubGoalRequest request) {
        SubGoal subGoal = new SubGoal();
        subGoal.setGoal(new Goal(goalId));
        subGoal.setTitle(request.getTitle());
        subGoal.setDescription(request.getDescription());
        subGoal.setTargetValue(request.getTargetValue());
        subGoal.setXpReward(request.getXpReward());
        subGoal.setOrderIndex(request.getOrderIndex());
        subGoal.setStatus(EGoalStatus.ACTIVE);

        return subGoalRepository.save(subGoal);
    }

    private SubGoal createSubGoalFromTemplate(Long goalId, SubGoalTemplate template) {
        SubGoal subGoal = new SubGoal();
        subGoal.setGoal(new Goal(goalId));
        subGoal.setTitle(template.getTitle());
        subGoal.setDescription(template.getDescription());
        subGoal.setTargetValue(template.getDefaultTargetValue());
        subGoal.setXpReward(template.getDefaultXpReward());
        subGoal.setOrderIndex(template.getOrderIndex());
        subGoal.setStatus(EGoalStatus.ACTIVE);

        return subGoalRepository.save(subGoal);
    }

    private GoalDTO convertToDTO(Goal goal) {
        List<SubGoalDTO> subGoalDTOs = goal.getSubGoals().stream()
                .map(this::convertSubGoalToDTO)
                .collect(Collectors.toList());

        return GoalDTO.builder()
                .id(goal.getId())
                .trackingId(goal.getTracking().getId())
                .goalTemplateId(goal.getGoalTemplate() != null ? goal.getGoalTemplate().getId() : null)
                .title(goal.getTitle())
                .description(goal.getDescription())
                .targetValue(goal.getTargetValue())
                .currentValue(goal.getCurrentValue())
                .xpReward(goal.getXpReward())
                .createdDate(goal.getCreatedDate())
                .dueDate(goal.getDueDate())
                .completedDate(goal.getCompletedDate())
                .status(goal.getStatus())
                .type(goal.getType())
                .priority(goal.getPriority())
                .isCustom(goal.getIsCustom())
                .progressPercentage(goal.getProgressPercentage())
                .subGoals(subGoalDTOs)
                .build();
    }

    private SubGoalDTO convertSubGoalToDTO(SubGoal subGoal) {
        return SubGoalDTO.builder()
                .id(subGoal.getId())
                .goalId(subGoal.getGoal().getId())
                .title(subGoal.getTitle())
                .description(subGoal.getDescription())
                .targetValue(subGoal.getTargetValue())
                .currentValue(subGoal.getCurrentValue())
                .xpReward(subGoal.getXpReward())
                .completedDate(subGoal.getCompletedDate())
                .status(subGoal.getStatus())
                .orderIndex(subGoal.getOrderIndex())
                .isCompleted(subGoal.isCompleted())
                .build();
    }
}
