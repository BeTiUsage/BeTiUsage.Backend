package home.betiusage.integrationTest;

import home.betiusage.entities.SubGoal;
import home.betiusage.repositories.SubGoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SubGoalIntegrationTest {

    @MockBean
    private SubGoalRepository subGoalRepository;

    @Autowired
    private WebTestClient webClient;

    private SubGoal mockSubGoal;
    private SubGoal mockSubGoal2;


    @BeforeEach
    void setUp() {
        mockSubGoal = new SubGoal();
        mockSubGoal.setId(1L);
        mockSubGoal.setName("Test SubGoal");
        mockSubGoal.setCompleted(true);

        mockSubGoal2 = new SubGoal();
        mockSubGoal2.setId(2L);
        mockSubGoal2.setName("Test SubGoal 2");
        mockSubGoal2.setCompleted(false);

        when(subGoalRepository.findById(1L)).thenReturn(Optional.of(mockSubGoal));
        when(subGoalRepository.findAll()).thenReturn(List.of(mockSubGoal));

        when(subGoalRepository.save(mockSubGoal)).thenReturn(mockSubGoal);
        when(subGoalRepository.save(mockSubGoal2)).thenReturn(mockSubGoal2);

        when(subGoalRepository.findById(2L)).thenReturn(Optional.of(mockSubGoal2));
        when(subGoalRepository.findAll()).thenReturn(List.of(mockSubGoal, mockSubGoal2));
    }


    @Test
    void testGetSubGoals() {
        webClient
                .get().uri("/api/public/goals")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].completed").isEqualTo(true);
    }

    @Test
    void testCreateSubGoal() {
        webClient
                .post().uri("/api/public/goals")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mockSubGoal)
                .exchange()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Test SubGoal")
                .jsonPath("$.completed").isEqualTo(true);
    }


    @Test
    void testUpdateSubGoal() {
        mockSubGoal.setName("Updated SubGoal");
        mockSubGoal.setCompleted(false);

        System.out.println(mockSubGoal.getName());
        System.out.println(mockSubGoal.getCompleted());

        webClient
                .put().uri("/api/public/goals/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mockSubGoal)
                .exchange()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Updated SubGoal")
                .jsonPath("$.completed").isEqualTo(false);
    }


    @Test
    void testDeleteSubGoal() {
        webClient
                .delete().uri("/api/public/goals/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.completed").isEqualTo(false);
    }
}
