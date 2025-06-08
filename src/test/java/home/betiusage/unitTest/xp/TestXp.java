package home.betiusage.unitTest.xp;

import home.betiusage.entities.Goal;
import home.betiusage.entities.SubGoal;
import home.betiusage.entities.Tracking;
import home.betiusage.repositories.TrackingRepository;
import home.betiusage.services.GoalService;
import home.betiusage.services.SubGoalService;
import home.betiusage.services.XpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestXp {

    @Mock
    private TrackingRepository trackingRepository;

    @InjectMocks
    private SubGoalService subGoalService;

    @InjectMocks
    private GoalService goalService;

    private Tracking tracking;
    private Goal goal;
    private SubGoal subGoal;
    private SubGoal subGoal2;

    @BeforeEach
    void setUp() {
        tracking = new Tracking();
        tracking.setId(1L);
        tracking.setXp(0);

        goal = new Goal();
        goal.setId(1L);
        goal.setName("Test Goal");
        goal.setCompleted(false);
        goal.setTracking(tracking);
        goal.setSubGoals(new ArrayList<>());

        subGoal = new SubGoal();
        subGoal.setId(1L);
        subGoal.setName("Test SubGoal");
        subGoal.setCompleted(false);
        subGoal.setGoal(goal);

        subGoal2 = new SubGoal();
        subGoal2.setId(2L);
        subGoal2.setName("Test SubGoal 2");
        subGoal2.setCompleted(false);
        subGoal2.setGoal(goal);

        goal.getSubGoals().add(subGoal);
    }

    @Test
    void testXpForSubgoalCompletion() {
        when(trackingRepository.save(any(Tracking.class))).thenReturn(tracking);

        subGoalService.awardXpForSubgoalCompletion(subGoal);

        assertEquals(XpService.getSubgoalCompletionXp(), tracking.getXp(),
                "Subgoal completion should award 10 XP");

        verify(trackingRepository, times(1)).save(tracking);
    }

    @Test
    void testXpForGoalCompletion() {
        when(trackingRepository.save(any(Tracking.class))).thenReturn(tracking);

        goalService.awardXpForGoalCompletion(goal);

        assertEquals(XpService.getGoalCompletionBonusXp(), tracking.getXp(),
                "Goal completion should award 50 XP bonus");

        verify(trackingRepository, times(1)).save(tracking);
    }

    @Test
    void testXpForCompletingSubgoalAndThenGoal() {
        when(trackingRepository.save(any(Tracking.class))).thenReturn(tracking);

        subGoalService.awardXpForSubgoalCompletion(subGoal);

        assertEquals(XpService.getSubgoalCompletionXp(), tracking.getXp(),
                "Subgoal completion should award 10 XP");

        goalService.awardXpForGoalCompletion(goal);

        int expectedTotalXp = XpService.getSubgoalCompletionXp() + XpService.getGoalCompletionBonusXp();
        assertEquals(expectedTotalXp, tracking.getXp(),
                "Total XP should be 60 after completing both subgoal and goal");

        verify(trackingRepository, times(2)).save(tracking);
    }
}