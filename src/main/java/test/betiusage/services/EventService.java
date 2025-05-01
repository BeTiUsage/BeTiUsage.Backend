package test.betiusage.services;

import org.springframework.stereotype.Service;
import test.betiusage.dto.EventDTO;
import test.betiusage.entitys.Event;
import test.betiusage.repositorys.EventRepository;

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
        eventDTO.setCategoryName(event.getHobby().getCategories().get(0).getName());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setStartTime(event.getStartTime());
        eventDTO.setEndTime(event.getEndTime());
        eventDTO.setTicketPrice(event.getTicketPrice());
        return eventDTO;
    }
}
