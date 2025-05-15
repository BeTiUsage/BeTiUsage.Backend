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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        goal.getSubGoals().add(subGoal);
    }

    @Test
    void testSubGoalCompletionGives10Xp() {
        // Arrange
        SubGoalDTO subGoalDTO = new SubGoalDTO();
        subGoalDTO.setId(1L);
        subGoalDTO.setCompleted(true);

        when(subGoalRepository.findById(1L)).thenReturn(Optional.of(subGoal));
        when(subGoalRepository.save(any(SubGoal.class))).thenReturn(subGoal);

        ArgumentCaptor<Tracking> trackingCaptor = ArgumentCaptor.forClass(Tracking.class);

        // Act
        subGoalService.updateSubGoalCompletedStatus(subGoalDTO, 1L);

        // Assert
        verify(trackingRepository).save(trackingCaptor.capture());
        Tracking savedTracking = trackingCaptor.getValue();
        assertEquals(XpService.getSubgoalCompletionXp(), savedTracking.getXp());
        System.out.println("SubGoal XP test - Expected: 10, Actual: " + savedTracking.getXp());
        assertEquals(10, savedTracking.getXp()); // Explicitly check the value
    }

    @Test
    void testGoalCompletionGives50Xp() {
        // Arrange
        // First, mark the subgoal as completed
        subGoal.setCompleted(true);

        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(1L);
        goalDTO.setCompleted(true);

        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        when(goalRepository.save(any(Goal.class))).thenReturn(goal);

        ArgumentCaptor<Tracking> trackingCaptor = ArgumentCaptor.forClass(Tracking.class);

        // Act
        goalService.updateGoalCompletedStatus(goalDTO, 1L);

        // Assert
        verify(trackingRepository).save(trackingCaptor.capture());
        Tracking savedTracking = trackingCaptor.getValue();
        assertEquals(XpService.getGoalCompletionBonusXp(), savedTracking.getXp());
        System.out.println("Goal XP test - Expected: 50, Actual: " + savedTracking.getXp());
        assertEquals(50, savedTracking.getXp()); // Explicitly check the value
    }

    @Test
    void testCompletingBothSubGoalAndGoalGivesTotal60Xp() {
        // Arrange
        tracking.setXp(0);

        // Set up subgoal completion
        SubGoalDTO subGoalDTO = new SubGoalDTO();
        subGoalDTO.setId(1L);
        subGoalDTO.setCompleted(true);

        when(subGoalRepository.findById(1L)).thenReturn(Optional.of(subGoal));
        when(subGoalRepository.save(any(SubGoal.class))).thenAnswer(invocation -> {
            SubGoal saved = invocation.getArgument(0);
            saved.setCompleted(true);
            return saved;
        });

        // Set up goal completion
        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(1L);
        goalDTO.setCompleted(true);

        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        when(goalRepository.save(any(Goal.class))).thenReturn(goal);

        ArgumentCaptor<Tracking> trackingCaptor = ArgumentCaptor.forClass(Tracking.class);

        // Act - First complete the subgoal
        subGoalService.updateSubGoalCompletedStatus(subGoalDTO, 1L);

        // Capture the intermediate XP value
        verify(trackingRepository, times(1)).save(trackingCaptor.capture());
        Tracking trackingAfterSubgoal = trackingCaptor.getValue();

        // Update tracking XP for next test
        tracking.setXp(trackingAfterSubgoal.getXp());

        // Act - Then complete the goal
        goalService.updateGoalCompletedStatus(goalDTO, 1L);

        // Assert the final state
        verify(trackingRepository, times(2)).save(trackingCaptor.capture());
        Tracking finalTracking = trackingCaptor.getAllValues().get(1);

        // The total XP should be subgoal (10) + goal (50) = 60
        System.out.println("Total XP test - Expected: 60, Actual: " + finalTracking.getXp());
        assertEquals(60, finalTracking.getXp());
    }
}