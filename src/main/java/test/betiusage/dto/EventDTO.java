package test.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String HobbyName;
    private String Name;
    private String Description;
    private String Location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double TicketPrice;
}
