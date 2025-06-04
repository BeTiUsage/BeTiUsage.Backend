package home.betiusage.services;

import home.betiusage.entities.Goal;
import home.betiusage.entities.SubGoal;
import home.betiusage.entities.Tracking;
import home.betiusage.repositories.TrackingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubGoalService {
    private final TrackingRepository trackingRepository;

    public SubGoalService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    public void awardXpForSubgoalCompletion(SubGoal subGoal) {
        Goal parentGoal = subGoal.getGoal();
        if (parentGoal == null || parentGoal.getTracking() == null) {
            return; // No tracking to update
        }

        Tracking tracking = parentGoal.getTracking();

        // Initialize XP if it's null
        if (tracking.getXp() == null) {
            tracking.setXp(0);
        }

        // Award XP for subgoal completion
        tracking.setXp(tracking.getXp() + XpService.getSubgoalCompletionXp());

        // Save the tracking with updated XP
        trackingRepository.save(tracking);
    }
}
