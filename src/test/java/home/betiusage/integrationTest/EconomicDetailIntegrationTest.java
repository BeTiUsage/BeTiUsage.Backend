package home.betiusage.integrationTest;

import home.betiusage.entities.EconomicDetail;
import home.betiusage.entities.Hobby;
import home.betiusage.repositories.EconomicDetailRepository;
import home.betiusage.repositories.HobbyRepository;
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
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EconomicDetailIntegrationTest {

    @MockBean
    private HobbyRepository hobbyRepository;

    @MockBean
    private EconomicDetailRepository economicDetailRepository;

    @Autowired
    private WebTestClient webClient;

    private Hobby mockHobby;
    private EconomicDetail mockEconomicDetail;

    @BeforeEach
    void setUp() {
        mockHobby = new Hobby();
        mockHobby.setId(1L);
        mockHobby.setName("Test Hobby");
        mockHobby.setAverageCapital(200.0);
        mockHobby.setMinimumStartCapital(100.0);

        mockEconomicDetail = new EconomicDetail();
        mockEconomicDetail.setId(1L);
        mockEconomicDetail.setLabel("Hiking Boots");
        mockEconomicDetail.setEstimatedCost(120.0);
        mockEconomicDetail.setCostRangeMin(100.0);
        mockEconomicDetail.setCostRangeMax(150.0);
        mockEconomicDetail.setIsRequired(true);
        mockEconomicDetail.setLocationDependent(false);
        mockEconomicDetail.setComment("Essential boots for hiking. Waterproof and durable.");
        mockEconomicDetail.setCurrency("USD");
        mockEconomicDetail.setPurchaseLink("https://example.com/hiking-boots");
        mockEconomicDetail.setDuration("N/A");
        mockEconomicDetail.setHobby(mockHobby);

        when(hobbyRepository.existsById(1L)).thenReturn(true);
        when(hobbyRepository.findById(1L)).thenReturn(Optional.of(mockHobby));
        when(economicDetailRepository.findAllByHobbyId(mockHobby.getId())).thenReturn(List.of(mockEconomicDetail));
    }

    @Test
    void getEconomicDetailsByHobbyId_shouldReturnDetails() {
        webClient
                .get().uri("/api/economicdetails/hobbyid/{id}", mockHobby.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].label").isEqualTo("Hiking Boots")
                .jsonPath("$[0].estimatedCost").isEqualTo(120.0)
                .jsonPath("$[0].currency").isEqualTo("USD")
                .jsonPath("$[0].purchaseLink").isEqualTo("https://example.com/hiking-boots")
                .jsonPath("$[0].hobbyId").isEqualTo(1)
                .jsonPath("$[0].minimumStartCapital").isEqualTo(100.0)
                .jsonPath("$[0].averageCapital").isEqualTo(200.0);
    }
}
