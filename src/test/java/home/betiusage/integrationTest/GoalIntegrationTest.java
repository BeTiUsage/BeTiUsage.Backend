package home.betiusage.integrationTest;

import home.betiusage.entities.Goal;
import home.betiusage.entities.Tracking;
import home.betiusage.repositories.GoalRepository;
import home.betiusage.repositories.TrackingRepository;
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

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class GoalIntegrationTest {

    @MockBean
    private GoalRepository goalRepository;

    @MockBean
    private TrackingRepository trackingRepository;

    @Autowired
    private WebTestClient webClient;

    private Goal mockGoal;
    private Tracking mockTracking;

    @BeforeEach
    void setUp() {

        // create tracking
        mockTracking = new Tracking();
        mockTracking.setId(1L);
        mockTracking.setXp(20);
        mockTracking.setMoneySpent(0.0);
        mockTracking.setStartDate(LocalDateTime.now());


        // create goal
        mockGoal = new Goal();
        mockGoal.setId(1L);
        mockGoal.setName("Test Goal");
        mockGoal.setCompleted(false);
        mockGoal.setTracking(mockTracking);

        when(goalRepository.findById(1L)).thenReturn(Optional.of(mockGoal));
        when(goalRepository.findAll()).thenReturn(List.of(mockGoal));
        when(goalRepository.save(mockGoal)).thenReturn(mockGoal);

        when(trackingRepository.findById(1L)).thenReturn(Optional.of(mockTracking));
        when(trackingRepository.findAll()).thenReturn(List.of(mockTracking));
    }

    @Test
    void testGetGoals() {
        webClient
                .get().uri("/api/public/goals")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo("Test Goal")
                .jsonPath("$[0].completed").isEqualTo(false);
    }


    @Test
    void testCreateGoal() {
        webClient
                .post().uri("/api/public/goals")
                .bodyValue(mockGoal)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Test Goal")
                .jsonPath("$.completed").isEqualTo(false);
    }

}
