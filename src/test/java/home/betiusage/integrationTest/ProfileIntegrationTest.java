package home.betiusage.integrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import home.betiusage.entities.Profile;
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
        mockProfile.setUsername("JohnDoe");

        when(profileRepository.findById(1L)).thenReturn(Optional.of(mockProfile));
        when(profileRepository.findAll()).thenReturn(List.of(mockProfile));
        when(profileRepository.existsById(1L)).thenReturn(true);
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
                .jsonPath("$[0].username").isEqualTo("JohnDoe");

        verify(profileRepository, times(1)).findAll();
    }

    @Test
    void getProfilesFindById() {
        webClient
                .get().uri("/api/profiles/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.email").isEqualTo("Johntestdoe@gmail.com")
                .jsonPath("$.username").isEqualTo("JohnDoe");

        verify(profileRepository, times(1)).findById(1L);
    }

    @Test
    void postProfilesCreateProfile() {
        webClient
                .post().uri("/api/profiles/createProfile")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new Profile("JohnDoe", "Johntestdoe@gmail.com"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.email").isEqualTo("Johntestdoe@gmail.com")
                .jsonPath("$.username").isEqualTo("JohnDoe");

        verify(profileRepository, times(1)).save(any(Profile.class));
    }

    @Test
    void putProfilesUpdateProfile() {

        webClient
                .put().uri("/api/profiles/updateProfile/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                            {
                            "username": "JohnDoeNEW"
                            }
                            """)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.username").isEqualTo("JohnDoeNEW");

        verify(profileRepository, times(1)).save(any(Profile.class));
    }
}