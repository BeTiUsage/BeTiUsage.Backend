package home.betiusage.integrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import home.betiusage.entities.Category;
import home.betiusage.entities.Event;
import home.betiusage.entities.Hobby;
import home.betiusage.repositories.CategoryRepository;
import home.betiusage.repositories.EventRepository;
import home.betiusage.repositories.HobbyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EventIntegrationTest {

    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private HobbyRepository hobbyRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private WebTestClient webClient;

    private Event mockEvent;
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
        mockHobby.setName("Test Hobby");
        mockHobby.setCategories(List.of(mockCategory));

        mockCategory.setHobbies(List.of(mockHobby));

        mockEvent = new Event();
        mockEvent.setId(1L);
        mockEvent.setName("Test Event");
        mockEvent.setHobby(mockHobby);
        mockEvent.setDescription("This is a test event.");
        mockEvent.setLocation("Test Location");
        mockEvent.setStartTime(LocalDateTime.parse("2023-10-01T08:00:00"));
        mockEvent.setEndTime(LocalDateTime.parse("2023-10-01T12:00:00"));
        mockEvent.setTicketPrice(20.0);
        mockEvent.setCity("Test City");

        when(eventRepository.findById(1L)).thenReturn(Optional.of(mockEvent));
        when(hobbyRepository.findById(1L)).thenReturn(Optional.of(mockHobby));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));
        when(eventRepository.findAll()).thenReturn(List.of(mockEvent));
    }

    @Test
    void notNull() {
        assertThat(webClient).isNotNull();
    }

    @Test
    void getEvents() {
        webClient
                .get().uri("/api/events")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].hobbyName").isEqualTo("Test Hobby")
                .jsonPath("$[0].name").isEqualTo("Test Event")
                .jsonPath("$[0].description").isEqualTo("This is a test event.")
                .jsonPath("$[0].location").isEqualTo("Test Location")
                .jsonPath("$[0].startTime").isEqualTo("2023-10-01T08:00:00")
                .jsonPath("$[0].endTime").isEqualTo("2023-10-01T12:00:00")
                .jsonPath("$[0].ticketPrice").isEqualTo(20.0)
                .jsonPath("$[0].city").isEqualTo("Test City");
    }
}
