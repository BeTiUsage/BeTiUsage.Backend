package home.betiusage.integrationTest;

import home.betiusage.entities.Community;
import home.betiusage.entities.Hobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import home.betiusage.repositories.CommunityRepository;
import home.betiusage.repositories.HobbyRepository;
import home.betiusage.services.CommunityService;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ComponentScan(basePackageClasses = {CommunityService.class})
public class CommunityIntegrationTest {
    @MockBean
    CommunityRepository communityRepository;

    @MockBean
    HobbyRepository hobbyRepository;

    @Autowired
    WebTestClient webClient;

    private Community mockCommunity;
    private Hobby mockHobby;

    @BeforeEach
    void setUp() {
        mockHobby = new Hobby();
        mockHobby.setId(1L);
        mockHobby.setName("test hobby");

        mockCommunity = new Community();
        mockCommunity.setId(1L);
        mockCommunity.setDescription("test description");
        mockCommunity.setUrl("test url");
        mockCommunity.setHobby(mockHobby);

        when(communityRepository.findById(1L)).thenReturn(Optional.of(mockCommunity));
        when(hobbyRepository.findById(1L)).thenReturn(Optional.of(mockHobby));
        when(communityRepository.findByHobbyId(1L)).thenReturn(List.of(mockCommunity));
        when(communityRepository.findAll()).thenReturn(List.of(mockCommunity));
        when(communityRepository.existsById(1L)).thenReturn(true);
        when(hobbyRepository.existsById(1L)).thenReturn(true);
    }

    @Test
    void notNull() {
        assertThat(webClient).isNotNull();
    }

    @Test
    void getCommunitiesFindAll() {
        webClient
                .get().uri("/api/communities")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1L)
                .jsonPath("$[0].url").isEqualTo("test url")
                .jsonPath("$[0].description").isEqualTo("test description")
                .jsonPath("$[0].hobbyId").isEqualTo(1L)
                .jsonPath("$[0].hobbyName").isEqualTo("test hobby");

        verify(communityRepository, times(1)).findAll();
    }

    @Test
    void getCommunitiesFindById() {
        webClient
                .get().uri("/api/communities/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.url").isEqualTo("test url")
                .jsonPath("$.description").isEqualTo("test description")
                .jsonPath("$.hobbyId").isEqualTo(1L)
                .jsonPath("$.hobbyName").isEqualTo("test hobby");

        verify(communityRepository, times(1)).findById(1L);
    }

    @Test
    void getCommunitiesFindByHobbyId() {
        webClient
                .get().uri("/api/communities/hobby/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1L)
                .jsonPath("$[0].url").isEqualTo("test url")
                .jsonPath("$[0].description").isEqualTo("test description")
                .jsonPath("$[0].hobbyId").isEqualTo(1L)
                .jsonPath("$[0].hobbyName").isEqualTo("test hobby");

        verify(communityRepository, times(1)).findByHobbyId(1L);
    }
}