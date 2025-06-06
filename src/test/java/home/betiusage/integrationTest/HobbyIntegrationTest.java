package home.betiusage.integrationTest;

import home.betiusage.enums.ECostRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import home.betiusage.entities.Category;
import home.betiusage.entities.Hobby;
import home.betiusage.repositories.CategoryRepository;
import home.betiusage.repositories.HobbyRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HobbyIntegrationTest {

    @MockBean
    private HobbyRepository hobbyRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private WebTestClient webClient;

    private Hobby mockHobby;
    private Category mockCategory;

    @BeforeEach
    void setUp() {
        mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setName("Outdoor");
        mockCategory.setSocial(true);

        mockHobby = new Hobby();
        mockHobby.setId(1L);
        mockHobby.setName("Hiking");
        mockHobby.setDescription("A long walk in nature.");
        mockHobby.setAverageTimeConsumption("2-3 hours");
        mockHobby.setMinimumStartCapital(100.0);
        mockHobby.setAverageStartCapital(200.0);
        mockHobby.setCostRating(ECostRating.CHEAP);
        mockHobby.setImg("https://as1.ftcdn.net/v2/jpg/02/91/63/48/1000_F_291634839_WF8mQtlpNZFlG1X34p8RX2dGQhUxynJK.jpg");

        mockHobby.setCategories(List.of(mockCategory));

        when(hobbyRepository.findById(1L)).thenReturn(Optional.of(mockHobby));
        when(hobbyRepository.findAll()).thenReturn(List.of(mockHobby));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));
    }

    @Test
    void notNull() {
        assertThat(webClient).isNotNull();
    }


    @Test
    void getAllHobbies() {
        webClient.get()
                .uri("/api/hobbies")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo("Hiking")
                .jsonPath("$[0].description").isEqualTo("A long walk in nature.")
                .jsonPath("$[0].averageTimeConsumption").isEqualTo("2-3 hours")
                .jsonPath("$[0].minimumStartCapital").isEqualTo(100.0)
                .jsonPath("$[0].averageStartCapital").isEqualTo(200.0)
                .jsonPath("$[0].costRating").isEqualTo("CHEAP")
                .jsonPath("$[0].img").isEqualTo("https://as1.ftcdn.net/v2/jpg/02/91/63/48/1000_F_291634839_WF8mQtlpNZFlG1X34p8RX2dGQhUxynJK.jpg")
                .jsonPath("$[0].categories[0].name").isEqualTo("Outdoor")
                .jsonPath("$[0].categories[0].social").isEqualTo(true);
    }
}
