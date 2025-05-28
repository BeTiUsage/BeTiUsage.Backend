package home.betiusage.integrationTest;

import home.betiusage.dto.TrackingDTO;
import home.betiusage.entities.Goal;
import home.betiusage.entities.Hobby;
import home.betiusage.entities.Profile;
import home.betiusage.entities.Tracking;
import home.betiusage.repositories.GoalRepository;
import home.betiusage.repositories.HobbyRepository;
import home.betiusage.repositories.ProfileRepository;
import home.betiusage.repositories.TrackingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TrackingIntegrationTest {

    @MockBean
    TrackingRepository trackingRepository;

    @MockBean
    GoalRepository goalRepository;

    @MockBean
    HobbyRepository hobbyRepository;

    @MockBean
    ProfileRepository profileRepository;

    @Autowired
    private WebTestClient webClient;

    private Tracking mockTracking;
    private Tracking mockTracking2;
    private Goal mockGoal;
    private Goal mockGoal2;
    private Hobby mockHobby;
    private Hobby mockHobby2;
    private Profile mockProfile;


    @BeforeEach
    void setUp() {
        mockHobby = new Hobby();
        mockHobby.setId(1L);
        mockHobby.setName("test hobby");

        mockHobby2 = new Hobby();
        mockHobby2.setId(2L);
        mockHobby2.setName("test hobby 2");

        mockGoal = new Goal();
        mockGoal.setId(1L);
        mockGoal.setName("test goal");

        mockGoal2 = new Goal();
        mockGoal2.setId(2L);
        mockGoal2.setName("test goal 2");

        mockProfile = new Profile();
        mockProfile.setId(1L);
        mockProfile.setUsername("test user");

        mockTracking = new Tracking();
        mockTracking.setId(1L);
        mockTracking.setHobby(mockHobby);
        mockTracking.setProfile(mockProfile);
        mockTracking.setGoals(List.of(mockGoal));
        mockTracking.setMoneySpent(0.0);

        mockTracking2 = new Tracking();
        mockTracking2.setId(2L);
        mockTracking2.setHobby(mockHobby2);
        mockTracking2.setProfile(mockProfile);
        mockTracking2.setGoals(List.of(mockGoal2));
        mockTracking2.setMoneySpent(10.0);



        when(profileRepository.findById(1L)).thenReturn(Optional.of(mockProfile));
        when(hobbyRepository.findById(1L)).thenReturn(Optional.of(mockHobby));
        when(goalRepository.findById(1L)).thenReturn(Optional.of(mockGoal));
        when(goalRepository.findById(2L)).thenReturn(Optional.of(mockGoal2));
        when(hobbyRepository.findById(2L)).thenReturn(Optional.of(mockHobby2));
        when(trackingRepository.findById(1L)).thenReturn(Optional.of(mockTracking));
        when(trackingRepository.findById(2L)).thenReturn(Optional.of(mockTracking2));
        when(trackingRepository.findAllByProfile_Id(1L)).thenReturn(List.of(mockTracking, mockTracking2));
        when(trackingRepository.findByIdAndProfile_Id(2L, 1L)).thenReturn(Optional.of(mockTracking2));
        when(trackingRepository.existsById(1L)).thenReturn(true);
        when(trackingRepository.existsById(2L)).thenReturn(true);
        when(profileRepository.existsById(1L)).thenReturn(true);
        when(hobbyRepository.existsById(1L)).thenReturn(true);
        when(hobbyRepository.existsById(2L)).thenReturn(true);
        when(goalRepository.existsById(1L)).thenReturn(true);
        when(goalRepository.existsById(2L)).thenReturn(true);
    }

    @Test
    void notNull() {
        assertThat(webClient).isNotNull();
    }


    @Test
    void findAllByProfileId () {
        webClient.
                get().uri("/api/trackings/profile/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1L)
                .jsonPath("$[0].hobbyId").isEqualTo(1L)
                .jsonPath("$[0].hobbyName").isEqualTo("test hobby")
                .jsonPath("$[0].goals[0].id").isEqualTo(1L)
                .jsonPath("$[0].profileId").isEqualTo(1L)
                .jsonPath("$[0].moneySpent").isEqualTo(0.0);
    }

    @Test
    void findByIdAndProfileId () {
        webClient.
                get().uri("/api/trackings/profile/1/tracking/2")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.id").isEqualTo(2L)
                .jsonPath("$.hobbyId").isEqualTo(2L)
                .jsonPath("$.hobbyName").isEqualTo("test hobby 2")
                .jsonPath("$.goals[0].id").isEqualTo(2L)
                .jsonPath("$.profileId").isEqualTo(1L)
                .jsonPath("$.moneySpent").isEqualTo(10.0);
    }

    @Test
    void createTracking() {
        // Create a TrackingDTO object to send in the request
        TrackingDTO requestDTO = new TrackingDTO();
        requestDTO.setProfileId(2L);
        requestDTO.setHobbyId(2L);
        requestDTO.setMoneySpent(0.0);
        requestDTO.setXp(0);
        requestDTO.setGoalId(new ArrayList<>());
        requestDTO.setStartDate(LocalDateTime.parse("2025-04-04T10:10:10"));

        Profile profile2 = new Profile();
        profile2.setId(2L);
        profile2.setUsername("test user 2");

        when(profileRepository.findById(2L)).thenReturn(Optional.of(profile2));
        when(profileRepository.existsById(2L)).thenReturn(true);

        Tracking savedTracking = new Tracking();
        savedTracking.setId(3L);
        savedTracking.setProfile(profile2);
        savedTracking.setHobby(mockHobby2);
        savedTracking.setGoals(new ArrayList<>());
        savedTracking.setMoneySpent(0.0);
        savedTracking.setXp(0);
        savedTracking.setStartDate(LocalDateTime.parse("2025-04-04T10:10:10"));

        when(trackingRepository.save(any(Tracking.class))).thenReturn(savedTracking);

        webClient
                .post().uri("/api/trackings")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(3L)
                .jsonPath("$.profileId").isEqualTo(2L)
                .jsonPath("$.hobbyId").isEqualTo(2L)
                .jsonPath("$.hobbyName").isEqualTo("test hobby 2")
                .jsonPath("$.moneySpent").isEqualTo(0.0);
    }

    @Test
    void updateTracking() {
        TrackingDTO requestDTO = new TrackingDTO();
        requestDTO.setProfileId(1L);
        requestDTO.setHobbyId(1L);
        requestDTO.setMoneySpent(5.0);
        requestDTO.setXp(10);
        requestDTO.setGoalId(new ArrayList<>());
        requestDTO.setStartDate(LocalDateTime.parse("2025-04-04T10:10:10"));

        when(trackingRepository.save(any(Tracking.class))).thenReturn(mockTracking);

        webClient
                .put().uri("/api/trackings/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.profileId").isEqualTo(1L)
                .jsonPath("$.hobbyId").isEqualTo(1L)
                .jsonPath("$.hobbyName").isEqualTo("test hobby")
                .jsonPath("$.moneySpent").isEqualTo(5.0);
    }
}
