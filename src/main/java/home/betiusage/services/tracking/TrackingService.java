package home.betiusage.services.tracking;

import home.betiusage.dto.tracking.TrackingDTO;
import home.betiusage.entities.Hobby;
import home.betiusage.entities.Profile;
import home.betiusage.entities.tracking.*;
import home.betiusage.enums.tracking.EGoalStatus;
import home.betiusage.repositories.tracking.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrackingService {


    private TrackingRepository trackingRepository;

    private GoalTemplateRepository goalTemplateRepository;
    private GoalRepository goalRepository;
    private SubGoalRepository subGoalRepository;

    public TrackingService(TrackingRepository trackingRepository, GoalTemplateRepository goalTemplateRepository, GoalRepository goalRepository, SubGoalRepository subGoalRepository, SubGoalTemplateRepository subGoalTemplateRepository, ActivityLogService activityLogService) {
        this.trackingRepository = trackingRepository;
        this.goalTemplateRepository = goalTemplateRepository;
        this.goalRepository = goalRepository;
        this.subGoalRepository = subGoalRepository;
        this.subGoalTemplateRepository = subGoalTemplateRepository;
        this.activityLogService = activityLogService;
    }

    private SubGoalTemplateRepository subGoalTemplateRepository;
    private ActivityLogService activityLogService;

    public TrackingDTO startHobbyTracking(Long profileId, Long hobbyId) {
        Optional<Tracking> existingTracking = trackingRepository.findByProfileIdAndHobbyId(profileId, hobbyId);
        if (existingTracking.isPresent()) {
            throw new IllegalStateException("Tracking for this hobby already exists");
        }

        Tracking tracking = new Tracking();
        tracking.setProfile(new Profile(profileId));
        tracking.setHobby(new Hobby(hobbyId));
        tracking.setStartDate(LocalDateTime.now());
        tracking.setCurrentLevel(1);
        tracking.setTotalXp(0);
        tracking.setIsActive(true);

        tracking = trackingRepository.save(tracking);

        createInitialGoalsFromTemplates(tracking);

        return convertToDTO(tracking);
    }

    public List<TrackingDTO> getUserTrackings(Long profileId) {
        List<Tracking> trackings = trackingRepository.findByProfileIdAndIsActive(profileId, true);
        return trackings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TrackingDTO getTracking(Long trackingId) {
        Tracking tracking = trackingRepository.findById(trackingId)
                .orElseThrow(() -> new EntityNotFoundException("Tracking not found"));
        return convertToDTO(tracking);
    }

    public TrackingDTO addXp(Long trackingId, Integer xp, String reason) {
        Tracking tracking = trackingRepository.findById(trackingId)
                .orElseThrow(() -> new EntityNotFoundException("Tracking not found"));

        Integer oldLevel = tracking.getCalculatedLevel();
        tracking.setTotalXp(tracking.getTotalXp() + xp);
        Integer newLevel = tracking.getCalculatedLevel();

        if (newLevel > oldLevel) {
            tracking.setCurrentLevel(newLevel);
            activityLogService.logLevelUp(tracking, newLevel);
        }

        tracking = trackingRepository.save(tracking);
        return convertToDTO(tracking);
    }

    public void updateStreak(Long trackingId) {
        Tracking tracking = trackingRepository.findById(trackingId)
                .orElseThrow(() -> new EntityNotFoundException("Tracking not found"));

        LocalDate today = LocalDate.now();
        LocalDate lastActivity = tracking.getLastActivityDate();

        if (lastActivity == null) {
            tracking.setCurrentStreak(1);
            tracking.setLongestStreak(1);
        } else if (lastActivity.equals(today.minusDays(1))) {
            tracking.setCurrentStreak(tracking.getCurrentStreak() + 1);
            if (tracking.getCurrentStreak() > tracking.getLongestStreak()) {
                tracking.setLongestStreak(tracking.getCurrentStreak());
            }
        } else if (!lastActivity.equals(today)) {
            tracking.setCurrentStreak(1);
        }

        tracking.setLastActivityDate(today);
        trackingRepository.save(tracking);
    }

    private void createInitialGoalsFromTemplates(Tracking tracking) {
        List<GoalTemplate> templates = goalTemplateRepository
                .findByHobbyIdAndLevelRequirementLessThanEqual(
                        tracking.getHobby().getId(),
                        tracking.getCurrentLevel()
                );

        for (GoalTemplate template : templates) {
            if (template.getIsActive()) {
                createGoalFromTemplate(tracking.getId(), template);
            }
        }
    }

    private Goal createGoalFromTemplate(Long trackingId, GoalTemplate template) {
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
                .findByGoalTemplateIdOrderByOrderIndex(template.getId());

        for (SubGoalTemplate subTemplate : subTemplates) {
            createSubGoalFromTemplate(goal.getId(), subTemplate);
        }

        return goal;
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

    private TrackingDTO convertToDTO(Tracking tracking) {
        return TrackingDTO.builder()
                .id(tracking.getId())
                .profileId(tracking.getProfile().getId())
                .hobbyId(tracking.getHobby().getId())
                .hobbyName(tracking.getHobby().getName())
                .startDate(tracking.getStartDate())
                .currentLevel(tracking.getCalculatedLevel())
                .totalXp(tracking.getTotalXp())
                .currentStreak(tracking.getCurrentStreak())
                .longestStreak(tracking.getLongestStreak())
                .lastActivityDate(tracking.getLastActivityDate())
                .isActive(tracking.getIsActive())
                .xpForNextLevel(tracking.getXpForNextLevel())
                .build();
    }
}