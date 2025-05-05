package home.betiusage.integrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import home.betiusage.entites.Profile;
import home.betiusage.repositories.ProfileRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProfileIntegrationTest {

    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    private WebTestClient webClient;

    private Profile mockProfile;

    @BeforeEach
    void setUp() {
        mockProfile = new Profile();
        mockProfile.setId(1L);
        mockProfile.setEmail("Johntestdoe@gmail.com");
        mockProfile.setFirstName("John");
        mockProfile.setMiddleName("home");
        mockProfile.setLastName("doe");

        when(profileRepository.findById(1L)).thenReturn(Optional.of(mockProfile));
        when(profileRepository.findAll()).thenReturn(List.of(mockProfile));
    }

    @Test
    void notNull() {
        assertThat(webClient).isNotNull();
    }

    @Test
    void getProfilesFindAll() {
        webClient
                .get().uri("/api/profiles")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].email").isEqualTo("Johntestdoe@gmail.com")
                .jsonPath("$[0].firstName").isEqualTo("John")
                .jsonPath("$[0].middleName").isEqualTo("home")
                .jsonPath("$[0].lastName").isEqualTo("doe");

        verify(profileRepository, times(1)).findAll();
    }
}