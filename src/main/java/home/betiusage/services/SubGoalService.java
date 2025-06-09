package home.betiusage.services;

import home.betiusage.entities.Goal;
import home.betiusage.entities.SubGoal;
import home.betiusage.entities.Tracking;
import home.betiusage.repositories.TrackingRepository;
import org.springframework.stereotype.Service;


@Service
public class SubGoalService {
    private final TrackingRepository trackingRepository;

    public SubGoalService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    public void awardXpForSubgoalCompletion(SubGoal subGoal) {
        Goal parentGoal = subGoal.getGoal();
        if (parentGoal == null || parentGoal.getTracking() == null) {
            return;
        }

        Tracking tracking = parentGoal.getTracking();

        if (tracking.getXp() == null) {
            tracking.setXp(0);
        }

        tracking.setXp(tracking.getXp() + XpService.getSubgoalCompletionXp());

        trackingRepository.save(tracking);
    }
}
