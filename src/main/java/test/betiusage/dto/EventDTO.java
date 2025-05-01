package test.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String HobbyName;
    private String CategoryName;
    private String Name;
    private String Description;
    private String Location;
    private LocalDate startTime;
    private LocalDate endTime;
    private Double TicketPrice;
}
