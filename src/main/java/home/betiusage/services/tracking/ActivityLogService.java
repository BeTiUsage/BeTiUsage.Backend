package home.betiusage.services.tracking;

import home.betiusage.dto.tracking.*;
import home.betiusage.entities.tracking.*;
import home.betiusage.repositories.tracking.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityLogService {


    private ActivityLogRepository activityLogRepository;

    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public void logGoalCompleted(Goal goal, String notes) {
        ActivityLog log = new ActivityLog();
        log.setTracking(goal.getTracking());
        log.setGoal(goal);
        log.setActivityType("GOAL_COMPLETED");
        log.setDescription("Completed goal: " + goal.getTitle());
        log.setXpGained(goal.getXpReward());
        log.setTimestamp(LocalDateTime.now());
        log.setNotes(notes);

        activityLogRepository.save(log);
    }

    public void logLevelUp(Tracking tracking, Integer newLevel) {
        ActivityLog log = new ActivityLog();
        log.setTracking(tracking);
        log.setActivityType("LEVEL_UP");
        log.setDescription("Reached level " + newLevel);
        log.setXpGained(0);
        log.setTimestamp(LocalDateTime.now());

        activityLogRepository.save(log);
    }

    public void logCustomActivity(Long trackingId, String description, Integer xp, String notes) {
        ActivityLog log = new ActivityLog();
        log.setTracking(new Tracking(trackingId));
        log.setActivityType("CUSTOM_ACTIVITY");
        log.setDescription(description);
        log.setXpGained(xp);
        log.setTimestamp(LocalDateTime.now());
        log.setNotes(notes);

        activityLogRepository.save(log);
    }

    public List<ActivityLogDTO> getTrackingActivities(Long trackingId) {
        List<ActivityLog> activities = activityLogRepository.findByTrackingIdOrderByTimestampDesc(trackingId);
        return activities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ActivityLogDTO> getRecentActivities(Long trackingId, Integer days) {
        LocalDateTime fromDate = LocalDateTime.now().minusDays(days);
        List<ActivityLog> activities = activityLogRepository.findRecentActivities(trackingId, fromDate);
        return activities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ActivityLogDTO convertToDTO(ActivityLog log) {
        return ActivityLogDTO.builder()
                .id(log.getId())
                .trackingId(log.getTracking().getId())
                .goalId(log.getGoal() != null ? log.getGoal().getId() : null)
                .subGoalId(log.getSubGoal() != null ? log.getSubGoal().getId() : null)
                .activityType(log.getActivityType())
                .description(log.getDescription())
                .xpGained(log.getXpGained())
                .timestamp(log.getTimestamp())
                .notes(log.getNotes())
                .build();
    }
}
