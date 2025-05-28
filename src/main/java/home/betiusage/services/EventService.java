package home.betiusage.services;

import org.springframework.stereotype.Service;
import home.betiusage.dto.EventDTO;
import home.betiusage.entities.Event;
import home.betiusage.repositories.EventRepository;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream().map(this::toDTO).toList();
    }

    private EventDTO toDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setHobbyName(event.getHobby().getName());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setStartTime(event.getStartTime());
        eventDTO.setEndTime(event.getEndTime());
        eventDTO.setTicketPrice(event.getTicketPrice());
        eventDTO.setCity(event.getCity());
        return eventDTO;
    }
}
