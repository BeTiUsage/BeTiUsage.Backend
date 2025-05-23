package home.betiusage.unitTest.xp;

import home.betiusage.dto.GoalDTO;
import home.betiusage.dto.SubGoalDTO;
import home.betiusage.entities.Goal;
import home.betiusage.entities.SubGoal;
import home.betiusage.entities.Tracking;
import home.betiusage.repositories.GoalRepository;
import home.betiusage.repositories.SubGoalRepository;
import home.betiusage.repositories.TrackingRepository;
import home.betiusage.services.GoalService;
import home.betiusage.services.SubGoalService;
import home.betiusage.services.XpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class testXp {

    @Mock
    private SubGoalRepository subGoalRepository;

    @Mock
    private GoalRepository goalRepository;

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
        // Create test objects
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
        // Setup - Tracking starts with 0 XP
        when(trackingRepository.save(any(Tracking.class))).thenReturn(tracking);

        // Execute the method under test
        subGoalService.awardXpForSubgoalCompletion(subGoal);

        // Verify the XP increase is correct (should be 10 XP for a subgoal)
        assertEquals(XpService.getSubgoalCompletionXp(), tracking.getXp(),
                "Subgoal completion should award 10 XP");

        // Verify that trackingRepository.save was called once
        verify(trackingRepository, times(1)).save(tracking);
    }

    @Test
    void testXpForGoalCompletion() {
        // Setup - Tracking starts with 0 XP
        when(trackingRepository.save(any(Tracking.class))).thenReturn(tracking);

        // Execute the method under test
        goalService.awardXpForGoalCompletion(goal);

        // Verify the XP increase is correct (should be 50 XP for a goal)
        assertEquals(XpService.getGoalCompletionBonusXp(), tracking.getXp(),
                "Goal completion should award 50 XP bonus");

        // Verify that trackingRepository.save was called once
        verify(trackingRepository, times(1)).save(tracking);
    }

    @Test
    void testXpForCompletingSubgoalAndThenGoal() {
        // Setup - Tracking starts with 0 XP
        when(trackingRepository.save(any(Tracking.class))).thenReturn(tracking);

        // First complete a subgoal (10 XP)
        subGoalService.awardXpForSubgoalCompletion(subGoal);

        // Verify first XP award
        assertEquals(XpService.getSubgoalCompletionXp(), tracking.getXp(),
                "Subgoal completion should award 10 XP");

        // Then complete the goal (50 XP more)
        goalService.awardXpForGoalCompletion(goal);

        // Verify the total XP is now 60 (10+50)
        int expectedTotalXp = XpService.getSubgoalCompletionXp() + XpService.getGoalCompletionBonusXp();
        assertEquals(expectedTotalXp, tracking.getXp(),
                "Total XP should be 60 after completing both subgoal and goal");

        // Verify that trackingRepository.save was called twice (once for each completion)
        verify(trackingRepository, times(2)).save(tracking);
    }
}