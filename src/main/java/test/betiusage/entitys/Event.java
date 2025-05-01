package test.betiusage.entitys;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Hobby hobby;
    @ManyToOne
    private Category category;
    private String name;
    private String description;
    private String location;
    private LocalDate startTime;
    private LocalDate endTime;
    private Double ticketPrice;
}
