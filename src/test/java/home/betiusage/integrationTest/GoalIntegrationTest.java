package home.betiusage.integrationTest;

import home.betiusage.dto.GoalDTO;
import home.betiusage.entities.Goal;
import home.betiusage.entities.Tracking;
import home.betiusage.repositories.GoalRepository;
import home.betiusage.repositories.TrackingRepository;
import home.betiusage.services.GoalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class GoalIntegrationTest {

    @MockBean
    private GoalRepository goalRepository;

    @MockBean
    private GoalService goalService;

    @MockBean
    private TrackingRepository trackingRepository;

    @Autowired
    private WebTestClient webClient;

    private Goal mockGoal;
    private Goal mockGoal2;
    private GoalDTO mockGoalDTO;
    private Tracking mockTracking;
    private Goal updatedGoal;

    @BeforeEach
    void setUp() {
        mockTracking = new Tracking();
        mockTracking.setId(1L);
        mockTracking.setXp(20);
        mockTracking.setMoneySpent(0.0);
        mockTracking.setStartDate(LocalDateTime.now());

        mockGoal = new Goal();
        mockGoal.setId(1L);
        mockGoal.setName("Test Goal");
        mockGoal.setCompleted(false);
        mockGoal.setTracking(mockTracking);

        mockGoal2 = new Goal();
        mockGoal2.setId(2L);
        mockGoal2.setName("Test Goal 2");
        mockGoal2.setCompleted(false);
        mockGoal2.setTracking(mockTracking);

        updatedGoal = new Goal();
        updatedGoal.setId(1L);
        updatedGoal.setName("Updated Goal");
        updatedGoal.setCompleted(false);

        mockGoalDTO = new GoalDTO();
        mockGoalDTO.setId(mockGoal.getId());
        mockGoalDTO.setName(mockGoal.getName());
        mockGoalDTO.setCompleted(mockGoal.getCompleted());
        mockGoalDTO.setTrackingId(mockTracking.getId());


        when(goalRepository.findById(1L)).thenReturn(Optional.of(mockGoal));
        when(goalRepository.findAll()).thenReturn(List.of(mockGoal));
        when(goalRepository.save(mockGoal)).thenReturn(mockGoal);

        when(trackingRepository.findById(1L)).thenReturn(Optional.of(mockTracking));
        when(trackingRepository.findAll()).thenReturn(List.of(mockTracking));

        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(mockGoal.getId());
        goalDTO.setName(mockGoal.getName());
        goalDTO.setCompleted(mockGoal.getCompleted());

        when(goalService.deleteGoal(2L)).thenReturn(goalDTO);

        GoalDTO updatedGoalDTO = new GoalDTO();
        updatedGoalDTO.setId(updatedGoal.getId());
        updatedGoalDTO.setName(updatedGoal.getName());
        updatedGoalDTO.setCompleted(updatedGoal.getCompleted());

        when(goalService.updateGoal(any(GoalDTO.class), any(Long.class))).thenReturn(updatedGoalDTO);
        when(goalService.findAll()).thenReturn(List.of(goalDTO));
    }

    @Test
    void notNull() {
        assertThat(webClient).isNotNull();
    }


    @Test
    void getGoals() {
        webClient
                .get().uri("/api/goals")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo("Test Goal")
                .jsonPath("$[0].completed").isEqualTo(false);
    }


    @Test
    void postGoal() {

        GoalDTO requestDTO = new GoalDTO();
        requestDTO.setName("Test Goal 3");
        requestDTO.setCompleted(false);

        GoalDTO responseDTO = new GoalDTO();
        responseDTO.setId(3L);
        responseDTO.setName("Test Goal 3");
        responseDTO.setCompleted(false);

        when(goalService.createGoal(any(GoalDTO.class))).thenReturn(responseDTO);

        webClient
                .post().uri("/api/goals")
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.name").isEqualTo("Test Goal 3")
                .jsonPath("$.completed").isEqualTo(false);
    }

    @Test
    void putGoal() {
        webClient.put().uri("/api/goals/1")
                .bodyValue(updatedGoal)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Updated Goal")
                .jsonPath("$.completed").isEqualTo(false);
    }

    @Test
    void deleteGoal() {
        webClient.delete().uri("/api/goals/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Test Goal")
                .jsonPath("$.completed").isEqualTo(false);
    }
}
