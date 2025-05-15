package home.betiusage.integrationTest;

import home.betiusage.dto.SubGoalDTO;
import home.betiusage.entities.SubGoal;
import home.betiusage.repositories.SubGoalRepository;
import home.betiusage.services.SubGoalService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SubGoalIntegrationTest {

    @MockBean
    private SubGoalRepository subGoalRepository;

    @MockBean
    private SubGoalService subGoalService;

    @Autowired
    private WebTestClient webClient;

    private SubGoal mockSubGoal;
    private SubGoal mockSubGoal2;
    private SubGoal updatedSubGoal;


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

        // Updated version of mockSubGoal
        updatedSubGoal = new SubGoal();
        updatedSubGoal.setId(1L);
        updatedSubGoal.setName("Updated SubGoal");
        updatedSubGoal.setCompleted(false);

        // This is the important line that was missing - mock the findAll method
        when(subGoalRepository.findAll()).thenReturn(List.of(mockSubGoal));

        // Your existing mocks
        when(subGoalRepository.findById(1L)).thenReturn(Optional.of(mockSubGoal));
        when(subGoalRepository.findById(2L)).thenReturn(Optional.of(mockSubGoal2));
        when(subGoalRepository.save(mockSubGoal)).thenReturn(mockSubGoal);
        when(subGoalRepository.save(mockSubGoal2)).thenReturn(mockSubGoal2);

        SubGoalDTO subGoalDTO = new SubGoalDTO();
        subGoalDTO.setId(mockSubGoal2.getId());
        subGoalDTO.setName(mockSubGoal2.getName());
        subGoalDTO.setCompleted(mockSubGoal2.getCompleted());
        when(subGoalService.deleteSubGoal(2L)).thenReturn(subGoalDTO);

        SubGoalDTO updatedSubGoalDTO = new SubGoalDTO();
        updatedSubGoalDTO.setId(updatedSubGoal.getId());
        updatedSubGoalDTO.setName(updatedSubGoal.getName());
        updatedSubGoalDTO.setCompleted(updatedSubGoal.getCompleted());
        when(subGoalService.updateSubGoal(any(SubGoalDTO.class), any(Long.class))).thenReturn(updatedSubGoalDTO);


    }



    @Test
    void testGetSubGoals() {
        webClient
                .get().uri("/api/subgoals")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo("Test SubGoal")
                .jsonPath("$[0].completed").isEqualTo(true);
    }

    @Test
    void testCreateSubGoal() {
        // 1. Create a request DTO (without ID)
        SubGoalDTO requestDTO = new SubGoalDTO();
        requestDTO.setName("Test SubGoal 3");
        requestDTO.setCompleted(false);

        // 2. Create a response DTO (with ID) that the mock service will return
        SubGoalDTO responseDTO = new SubGoalDTO();
        responseDTO.setId(3L);
        responseDTO.setName("Test SubGoal 3");
        responseDTO.setCompleted(false);

        // 3. Mock the service method
        when(subGoalService.createSubGoal(any(SubGoalDTO.class))).thenReturn(responseDTO);

        // 4. Perform the test
        webClient
                .post().uri("/api/subgoals")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)  // Send the request DTO (no ID)
                .exchange()
                .expectStatus().isCreated()  // Expect 201 Created status
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.name").isEqualTo("Test SubGoal 3")
                .jsonPath("$.completed").isEqualTo(false);
    }

    @Test
    void testUpdateSubGoal() {
        webClient.put().uri("/api/subgoals/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedSubGoal)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Updated SubGoal")
                .jsonPath("$.completed").isEqualTo(false);
    }

    @Test
    void testDeleteSubGoal() {
        webClient
                .delete().uri("/api/subgoals/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.completed").isEqualTo(false);
    }
}
