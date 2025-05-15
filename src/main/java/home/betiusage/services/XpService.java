package home.betiusage.services;

import org.springframework.stereotype.Service;

@Service
public class XpService {
    // XP amount for completing a subgoal
    private static final int SUBGOAL_COMPLETION_XP = 10;

    // Bonus XP for completing an entire goal (all subgoals)
    private static final int GOAL_COMPLETION_BONUS_XP = 50;

    public static int getSubgoalCompletionXp() {
        return SUBGOAL_COMPLETION_XP;
    }

    public static int getGoalCompletionBonusXp() {
        return GOAL_COMPLETION_BONUS_XP;
    }
}