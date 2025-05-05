package home.betiusage.integrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import home.betiusage.entites.Category;
import home.betiusage.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CategoryIntegrationTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private WebTestClient webClient;

    private Category mockCategory;

    @BeforeEach
    void setUp() {
        mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setName("Outdoor");
        mockCategory.setSocial(true);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));
        when(categoryRepository.findAll()).thenReturn(List.of(mockCategory));
    }

    @Test
    void testgetCategories() {
        webClient
                .get().uri("/api/categories")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo("Outdoor")
                .jsonPath("$[0].social").isEqualTo(true);
    }
}
