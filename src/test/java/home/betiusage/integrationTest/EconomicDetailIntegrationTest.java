package home.betiusage.integrationTest;

import home.betiusage.dto.EconomicDetailsDTO;
import home.betiusage.services.EconomicDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EconomicDetailIntegrationTest {

    @MockBean
    private EconomicDetailService economicDetailService;

    @Autowired
    private WebTestClient webClient;

    private EconomicDetailsDTO mockDTO;

    @BeforeEach
    void setUp() {
        mockDTO = new EconomicDetailsDTO();
        mockDTO.setId(1L);
        mockDTO.setHobbyId(1L);
        mockDTO.setLabel("Hiking Boots");
        mockDTO.setEstimatedCost(120.0);
        mockDTO.setCostRangeMin(100.0);
        mockDTO.setCostRangeMax(150.0);
        mockDTO.setIsRequired(true);
        mockDTO.setLocationDependent(false);
        mockDTO.setComment("Essential boots for hiking. Waterproof and durable.");
        mockDTO.setCurrency("USD");
        mockDTO.setPurchaseLink("https://example.com/hiking-boots");
        mockDTO.setDuration("N/A");
        mockDTO.setMinimumStartCapital(100.0);
        mockDTO.setAverageCapital(200.0);
    }

    @Test
    void notNull() {
        assertThat(webClient).isNotNull();
    }

    @Test
    void getAllEconomicDetails() {
        List<EconomicDetailsDTO> mockDTOs = List.of(mockDTO);
        when(economicDetailService.getEconomicDetails()).thenReturn(mockDTOs);

        webClient
                .get().uri("/api/economicdetails")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].label").isEqualTo("Hiking Boots")
                .jsonPath("$[0].estimatedCost").isEqualTo(120.0)
                .jsonPath("$[0].currency").isEqualTo("USD")
                .jsonPath("$[0].purchaseLink").isEqualTo("https://example.com/hiking-boots")
                .jsonPath("$[0].hobbyId").isEqualTo(1)
                .jsonPath("$[0].minimumStartCapital").isEqualTo(100.0)
                .jsonPath("$[0].averageCapital").isEqualTo(200.0);

        verify(economicDetailService, times(1)).getEconomicDetails();
    }
}